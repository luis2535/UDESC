package com.example.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabfinal.adapter.ServicoAdapter;
import com.example.trabfinal.api.ApiClient;
import com.example.trabfinal.api.ServicoApi;
import com.example.trabfinal.model.ServicoResponse;
import com.example.trabfinal.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeusServicosActivity extends AppCompatActivity {

    private RecyclerView recyclerServicos;
    private ServicoApi servicoApi;
    private ServicoAdapter servicoAdapter;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_servicos);

        recyclerServicos = findViewById(R.id.recycler_servicos);
        ImageView backIcon = findViewById(R.id.back_icon);

        sessionManager = SessionManager.getInstance();

        servicoApi = ApiClient.createServicoApiService();

        recyclerServicos.setLayoutManager(new LinearLayoutManager(this));
        servicoAdapter = new ServicoAdapter(new ArrayList<>(), servico -> abrirDetalhesServico(servico)); // Callback para clique
        recyclerServicos.setAdapter(servicoAdapter);

        backIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MeusServicosActivity.this, PrincipalActivity.class);
            startActivity(intent);
            finish();
        });

        carregarServicosUsuarioAtual();
    }

    private void carregarServicosUsuarioAtual() {
        int userId = sessionManager.getUsuarioResponse().getUsuario().getId(); // Certifique-se de que getUserId() retorna o ID do usuário

        servicoApi.listarServicosPorUsuario(userId).enqueue(new Callback<List<ServicoResponse>>() {
            @Override
            public void onResponse(Call<List<ServicoResponse>> call, Response<List<ServicoResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Atualiza RecyclerView com os dados recebidos
                    servicoAdapter.updateServicos(response.body());
                } else {
                    Toast.makeText(MeusServicosActivity.this, "Nenhum serviço encontrado.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ServicoResponse>> call, Throwable t) {
                Toast.makeText(MeusServicosActivity.this, "Falha na comunicação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void abrirDetalhesServico(ServicoResponse servico) {
        Intent intent = new Intent(this, DetalhesServicoActivity.class);
        intent.putExtra("SERVICO_ID", servico.getServico().getId());
        startActivity(intent);
    }
}
