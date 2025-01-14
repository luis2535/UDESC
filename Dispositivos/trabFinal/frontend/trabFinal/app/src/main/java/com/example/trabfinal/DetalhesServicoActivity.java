package com.example.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabfinal.adapter.ServicoComentarioAdapter;
import com.example.trabfinal.api.ApiClient;
import com.example.trabfinal.api.ServicoApi;
import com.example.trabfinal.api.ServicoComentarioApi;
import com.example.trabfinal.model.ServicoComentarioRequest;
import com.example.trabfinal.model.ServicoComentarioResponse;
import com.example.trabfinal.model.ServicoResponse;
import com.example.trabfinal.model.UsuarioResponse;
import com.example.trabfinal.utils.SessionManager;

import java.math.BigDecimal;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesServicoActivity extends AppCompatActivity {

    private TextView nomeServico, valorServico, listaEspecialidades, descricaoServico, usuarioServico;
    private EditText comentarioInput, orcamentoInput, comentarioOrcamentoInput;
    private Button enviarComentarioBtn;
    private RecyclerView comentariosRecyclerView;

    private ServicoApi servicoApi;
    private ServicoComentarioApi servicoComentarioApi;
    private SessionManager sessionManager;
    private ServicoComentarioAdapter comentarioAdapter;

    private int servicoId;
    private int usuarioLogadoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_servico);

        inicializarComponentes();

        servicoApi = ApiClient.createServicoApiService();
        servicoComentarioApi = ApiClient.createServicoComentarioApiService();

        servicoId = getIntent().getIntExtra("SERVICO_ID", -1);

        UsuarioResponse usuarioResponse = SessionManager.getInstance().getUsuarioResponse();
        usuarioLogadoId = usuarioResponse.getUsuario().getId();

        carregarDetalhesServico(servicoId);

        ImageView backIcon = findViewById(R.id.back_icon);
        backIcon.setOnClickListener(v -> {
            Intent intent = new Intent(DetalhesServicoActivity.this, PrincipalActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void inicializarComponentes() {
        nomeServico = findViewById(R.id.nome_servico);
        valorServico = findViewById(R.id.valor_servico);
        listaEspecialidades = findViewById(R.id.lista_especialidades);
        descricaoServico = findViewById(R.id.descricao_servico);
        usuarioServico = findViewById(R.id.usuario_servico);
        comentarioInput = findViewById(R.id.comentario_input);
        orcamentoInput = findViewById(R.id.orcamento_input);
        comentarioOrcamentoInput = findViewById(R.id.comentario_orcamento_input);
        enviarComentarioBtn = findViewById(R.id.enviar_comentario_btn);

        comentariosRecyclerView = findViewById(R.id.comentarios_recycler_view);
        comentariosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void carregarDetalhesServico(int servicoId) {
        servicoApi.findServicoById(servicoId).enqueue(new Callback<ServicoResponse>() {
            @Override
            public void onResponse(Call<ServicoResponse> call, Response<ServicoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ServicoResponse servico = response.body();

                    nomeServico.setText(servico.getServico().getDescricao());
                    valorServico.setText("R$ " + servico.getServico().getValor());
                    descricaoServico.setText(servico.getServico().getValorDescricao());
                    usuarioServico.setText("Criado por: " + servico.getServico().getUsuario().getNome());

                    StringBuilder especialidadesBuilder = new StringBuilder();
                    for (int i = 0; i < servico.getEspecialidade().size(); i++) {
                        especialidadesBuilder.append(servico.getEspecialidade().get(i).getNome());
                        if (i < servico.getEspecialidade().size() - 1) {
                            especialidadesBuilder.append(", ");
                        }
                    }
                    listaEspecialidades.setText(especialidadesBuilder.toString());

                    comentarioAdapter = new ServicoComentarioAdapter(DetalhesServicoActivity.this,
                            new ArrayList<>(), servicoId);
                    comentariosRecyclerView.setAdapter(comentarioAdapter);

                    if (usuarioLogadoId == servico.getServico().getUsuario().getId()) {
                        mostrarComentarios(servico);
                        ocultarCamposDeComentario();
                    } else {
                        mostrarCamposDeComentario();
                    }
                } else {
                    Toast.makeText(DetalhesServicoActivity.this, "Erro ao carregar detalhes do serviço.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServicoResponse> call, Throwable t) {
                Toast.makeText(DetalhesServicoActivity.this, "Falha na comunicação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void mostrarComentarios(ServicoResponse servico) {
        comentariosRecyclerView.setVisibility(View.VISIBLE);

        if (servico.getServicoComentarioResponse() != null && !servico.getServicoComentarioResponse().isEmpty()) {
            comentarioAdapter.updateComentarios(servico.getServicoComentarioResponse());
        } else {
            Toast.makeText(this, "Nenhum comentário ainda.", Toast.LENGTH_SHORT).show();
        }
    }

    private void ocultarCamposDeComentario() {
        comentarioInput.setVisibility(View.GONE);
        orcamentoInput.setVisibility(View.GONE);
        comentarioOrcamentoInput.setVisibility(View.GONE);
        enviarComentarioBtn.setVisibility(View.GONE);
    }

    private void mostrarCamposDeComentario() {
        comentarioInput.setVisibility(View.VISIBLE);
        orcamentoInput.setVisibility(View.VISIBLE);
        comentarioOrcamentoInput.setVisibility(View.VISIBLE);
        enviarComentarioBtn.setVisibility(View.VISIBLE);

        enviarComentarioBtn.setOnClickListener(v -> enviarComentario());
    }

    private void enviarComentario() {
        // Criar o payload para o comentário
        String comentarioTexto = comentarioInput.getText().toString().trim();
        String comentarioOrcamento = comentarioOrcamentoInput.getText().toString().trim();
        BigDecimal orcamento;

        if (comentarioTexto.isEmpty() || comentarioOrcamento.isEmpty() || orcamentoInput.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            orcamento = new BigDecimal(orcamentoInput.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, insira um orçamento válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        ServicoComentarioRequest request = new ServicoComentarioRequest();
        request.setUsuarioId(usuarioLogadoId);
        request.setServicoId(servicoId);
        request.setComentario(comentarioTexto);
        request.setComentarioOrcamento(comentarioOrcamento);
        request.setOrcamento(orcamento);

        // Enviar o comentário via API
        servicoComentarioApi.enviarComentario(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetalhesServicoActivity.this, "Comentário enviado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish(); // Volta para a tela anterior
                } else {
                    Toast.makeText(DetalhesServicoActivity.this, "Erro ao enviar comentário.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DetalhesServicoActivity.this, "Falha na comunicação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
