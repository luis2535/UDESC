package com.example.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trabfinal.api.ApiClient;
import com.example.trabfinal.api.ServicoApi;
import com.example.trabfinal.model.ServicoCreateRequest;
import com.example.trabfinal.utils.SessionManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriarServico extends AppCompatActivity {

    private EditText nomeServicoInput, descricaoValorInput, valorServicoInput;
    private CheckBox desenvolvedor, design, java, javascript, springboot, react, vuejs, angular, html, css;
    private ServicoApi servicoApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_criar_servico);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nomeServicoInput = findViewById(R.id.nome_servico_input);
        descricaoValorInput = findViewById(R.id.descricao_valor_input);
        valorServicoInput = findViewById(R.id.valor_servico_input);

        desenvolvedor = findViewById(R.id.desenvolvedor);
        design = findViewById(R.id.design);
        java = findViewById(R.id.java);
        javascript = findViewById(R.id.javascript);
        springboot = findViewById(R.id.springboot);
        react = findViewById(R.id.react);
        vuejs = findViewById(R.id.vuejs);
        angular = findViewById(R.id.angular);
        html = findViewById(R.id.html);
        css = findViewById(R.id.css);

        servicoApi = ApiClient.createServicoApiService();

        Button criarServicoBtn = findViewById(R.id.criar_servico_btn);
        criarServicoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarServico();
            }
        });
        ImageView backIcon = findViewById(R.id.back_icon);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CriarServico.this, PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void criarServico() {
        String nomeServico = nomeServicoInput.getText().toString().trim();
        String descricaoValor = descricaoValorInput.getText().toString().trim();
        String valorStr = valorServicoInput.getText().toString().trim();
        BigDecimal valor;

        if (nomeServico.isEmpty() || descricaoValor.isEmpty() || valorStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            valor = new BigDecimal(valorStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor inválido. Use apenas números.", Toast.LENGTH_LONG).show();
            return;
        }

        List<Integer> especialidades = new ArrayList<>();
        if (desenvolvedor.isChecked()) especialidades.add(1);
        if (design.isChecked()) especialidades.add(2);
        if (java.isChecked()) especialidades.add(3);
        if (javascript.isChecked()) especialidades.add(4);
        if (springboot.isChecked()) especialidades.add(5);
        if (react.isChecked()) especialidades.add(6);
        if (vuejs.isChecked()) especialidades.add(7);
        if (angular.isChecked()) especialidades.add(8);
        if (html.isChecked()) especialidades.add(9);
        if (css.isChecked()) especialidades.add(10);

        if (especialidades.isEmpty()) {
            Toast.makeText(this, "Selecione pelo menos uma especialidade.", Toast.LENGTH_LONG).show();
            return;
        }

        Integer usuarioId = SessionManager.getInstance().getUsuarioResponse().getUsuario().getId();
        if (usuarioId == null) {
            Toast.makeText(this, "Erro: usuário não encontrado.", Toast.LENGTH_LONG).show();
            return;
        }

        ServicoCreateRequest request = new ServicoCreateRequest();
        request.setDescricao(nomeServico);
        request.setEspecialidades(especialidades);
        request.setValor(valor);
        request.setValorDescricao(descricaoValor);
        request.setUsuarioId(usuarioId);

        servicoApi.criarServico(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CriarServico.this, "Serviço criado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(CriarServico.this, "Erro ao criar serviço. Tente novamente.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CriarServico.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
