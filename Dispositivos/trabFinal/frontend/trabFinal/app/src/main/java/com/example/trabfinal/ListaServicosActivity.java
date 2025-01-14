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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaServicosActivity extends AppCompatActivity {

    private Button selectFiltersBtn;
    private RecyclerView recyclerServicos;
    private ServicoApi servicoApi;
    private ServicoAdapter servicoAdapter;
    private List<Integer> selectedEspecialidades = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_servicos);

        selectFiltersBtn = findViewById(R.id.select_filters_btn);
        recyclerServicos = findViewById(R.id.recycler_servicos);

        servicoApi = ApiClient.createServicoApiService();

        recyclerServicos.setLayoutManager(new LinearLayoutManager(this));
        servicoAdapter = new ServicoAdapter(new ArrayList<>(), servico -> abrirDetalhesServico(servico));
        recyclerServicos.setAdapter(servicoAdapter);

        selectFiltersBtn.setOnClickListener(v -> abrirDialogoDeSelecao());

        ImageView backIcon = findViewById(R.id.back_icon);
        backIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ListaServicosActivity.this, PrincipalActivity.class);
            startActivity(intent);
            finish();
        });

        carregarServicos(new ArrayList<>());
    }

    private void abrirDialogoDeSelecao() {
        String[] especialidades = {"Desenvolvedor", "Design", "Java", "Javascript", "Spring Boot", "React", "VueJs", "Angular", "HTML", "CSS"};
        boolean[] checkedEspecialidades = new boolean[especialidades.length];

        for (int i = 0; i < especialidades.length; i++) {
            checkedEspecialidades[i] = selectedEspecialidades.contains(i + 1);
        }

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Selecione Especialidades");
        builder.setMultiChoiceItems(especialidades, checkedEspecialidades, (dialog, which, isChecked) -> {
            if (isChecked) {
                if (!selectedEspecialidades.contains(which + 1)) {
                    selectedEspecialidades.add(which + 1);
                }
            } else {
                selectedEspecialidades.remove(Integer.valueOf(which + 1));
            }
        });

        builder.setPositiveButton("Filtrar", (dialog, which) -> carregarServicos(selectedEspecialidades));
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private void carregarServicos(List<Integer> especialidadesIds) {
        servicoApi.listarServicos(especialidadesIds).enqueue(new Callback<List<ServicoResponse>>() {
            @Override
            public void onResponse(Call<List<ServicoResponse>> call, Response<List<ServicoResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    servicoAdapter.updateServicos(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ServicoResponse>> call, Throwable t) {
                Toast.makeText(ListaServicosActivity.this, "Falha na comunicação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void abrirDetalhesServico(ServicoResponse servico) {
        Intent intent = new Intent(this, DetalhesServicoActivity.class);
        intent.putExtra("SERVICO_ID", servico.getServico().getId());
        startActivity(intent);
    }
}
