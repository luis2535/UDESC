package com.example.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabfinal.adapter.ServicoUsuarioAdapter;
import com.example.trabfinal.model.ServicoUsuarioResponse;
import com.example.trabfinal.utils.SessionManager;

import java.util.List;

public class MeusContratosActivity extends AppCompatActivity {

    private RecyclerView recyclerContratos;
    private ServicoUsuarioAdapter contratoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_contratos);

        recyclerContratos = findViewById(R.id.recycler_contratos);
        recyclerContratos.setLayoutManager(new LinearLayoutManager(this));

        ImageView backIcon = findViewById(R.id.back_icon);
        backIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MeusContratosActivity.this, PrincipalActivity.class);
            startActivity(intent);
            finish();
        });

        carregarContratos();
    }

    private void carregarContratos() {
        List<ServicoUsuarioResponse> contratos = SessionManager.getInstance()
                .getUsuarioResponse()
                .getServicoUsuarioResponses();

        if (contratos != null && !contratos.isEmpty()) {
            contratoAdapter = new ServicoUsuarioAdapter(this, contratos);
            recyclerContratos.setAdapter(contratoAdapter);
        } else {
            Toast.makeText(this, "Nenhum contrato encontrado.", Toast.LENGTH_SHORT).show();
        }
    }
}
