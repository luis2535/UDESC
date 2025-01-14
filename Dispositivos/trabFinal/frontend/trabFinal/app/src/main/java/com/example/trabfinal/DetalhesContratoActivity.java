package com.example.trabfinal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabfinal.model.ServicoUsuarioResponse;

public class DetalhesContratoActivity extends AppCompatActivity {

    private TextView nomeServico, valorContrato, descricaoServico, usuarioServico;
    private Button finalizarServicoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_contrato);

        nomeServico = findViewById(R.id.nome_servico);
        valorContrato = findViewById(R.id.valor_contrato);
        descricaoServico = findViewById(R.id.descricao_servico);
        usuarioServico = findViewById(R.id.usuario_servico);
        finalizarServicoButton = findViewById(R.id.finalizar_servico_btn);

        ImageView backIcon = findViewById(R.id.back_icon);
        backIcon.setOnClickListener(v -> finish());

        ServicoUsuarioResponse contrato = (ServicoUsuarioResponse) getIntent().getSerializableExtra("CONTRATO");

        if (contrato != null) {
            preencherDados(contrato);
        } else {
            Toast.makeText(this, "Erro ao carregar os detalhes do contrato.", Toast.LENGTH_SHORT).show();
            finish();
        }

        finalizarServicoButton.setOnClickListener(v -> finalizarServico());
    }

    private void preencherDados(ServicoUsuarioResponse contrato) {
        nomeServico.setText("Serviço: " + contrato.getServico().getDescricao());
        valorContrato.setText("Valor do Contrato: R$ " + contrato.getValor());
        descricaoServico.setText("Descrição: " + contrato.getServico().getValorDescricao());
        usuarioServico.setText("Criado por: " + contrato.getServico().getUsuario().getNome());
    }

    private void finalizarServico() {
        Toast.makeText(this, "Serviço finalizado com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
