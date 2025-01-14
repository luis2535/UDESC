package com.example.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trabfinal.model.UsuarioResponse;
import com.example.trabfinal.utils.SessionManager;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        UsuarioResponse usuarioResponse = SessionManager.getInstance().getUsuarioResponse();

        TextView nomeTextView = findViewById(R.id.nome_text);
        Button criarServicoButton = findViewById(R.id.criar_servico_btn);
        Button encontrarServicoButton = findViewById(R.id.encontrar_servico_btn);
        Button meusServicosButton = findViewById(R.id.meusServicos_btn);
        Button meusContartosButton = findViewById((R.id.meusContratos_btn));

        if (usuarioResponse != null) {
            String nome = usuarioResponse.getUsuario().getNome();
            int tipoUsuario = usuarioResponse.getUsuario().getTipoUsuario().getId();

            nomeTextView.setText("Bem-vindo, " + nome + "!");

            if (tipoUsuario == 1) {
                criarServicoButton.setVisibility(View.VISIBLE);
                encontrarServicoButton.setVisibility(View.GONE);
                meusServicosButton.setVisibility(View.VISIBLE);
                meusContartosButton.setVisibility(View.GONE);
            } else if (tipoUsuario == 2) {
                criarServicoButton.setVisibility(View.GONE);
                encontrarServicoButton.setVisibility(View.VISIBLE);
                meusServicosButton.setVisibility(View.GONE);
                meusContartosButton.setVisibility(View.VISIBLE);
            } else {
                criarServicoButton.setVisibility(View.GONE);
                encontrarServicoButton.setVisibility(View.GONE);
                meusServicosButton.setVisibility(View.GONE);
                meusContartosButton.setVisibility(View.GONE);
            }
        } else {
            nomeTextView.setText("Nenhum usuário logado.");
            Toast.makeText(this, "Erro: Nenhum usuário logado.", Toast.LENGTH_SHORT).show();
        }

        Button logoutButton = findViewById(R.id.logout_btn);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager.getInstance().setUsuarioResponse(null);

                Intent intent = new Intent(PrincipalActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        criarServicoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, CriarServico.class);
                startActivity(intent);
            }
        });

        encontrarServicoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, ListaServicosActivity.class);
                startActivity(intent);
            }
        });

        meusServicosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, MeusServicosActivity.class);
                startActivity(intent);
            }
        });

        Button meusContratosButton = findViewById(R.id.meusContratos_btn);

        meusContratosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, MeusContratosActivity.class);
                startActivity(intent);
            }
        });

    }
}
