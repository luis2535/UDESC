package com.example.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabfinal.api.ApiClient;
import com.example.trabfinal.api.UsuarioApi;
import com.example.trabfinal.model.UsuarioCreateRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_cadastro extends AppCompatActivity {

    private EditText nameInput, usernameInput, passwordInput, descricaoInput;
    private Spinner tipoUsuarioSpinner;
    private TextView especialidadesLabel;
    private LinearLayout especialidadesLayout;
    private CheckBox desenvolvedor, design, java, javascript, springBoot, react, vueJs, angular, html, css;
    private UsuarioApi usuarioApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        usuarioApi = ApiClient.createUsuarioApiService();

        nameInput = findViewById(R.id.name_input);
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        descricaoInput = findViewById(R.id.descricao_input);
        tipoUsuarioSpinner = findViewById(R.id.tipo_usuario_spinner);
        especialidadesLabel = findViewById(R.id.especialidades_label);
        especialidadesLayout = findViewById(R.id.especialidades_layout);

        desenvolvedor = findViewById(R.id.desenvolvedor);
        design = findViewById(R.id.design);
        java = findViewById(R.id.Java);
        javascript = findViewById(R.id.Javascript);
        springBoot = findViewById(R.id.SpringBoot);
        react = findViewById(R.id.React);
        vueJs = findViewById(R.id.VueJs);
        angular = findViewById(R.id.Angular);
        html = findViewById(R.id.Html);
        css = findViewById(R.id.CSS);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.tipo_usuario_array,
                R.layout.spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoUsuarioSpinner.setAdapter(adapter);

        tipoUsuarioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) {
                    descricaoInput.setVisibility(View.VISIBLE);
                    especialidadesLabel.setVisibility(View.VISIBLE);
                    especialidadesLayout.setVisibility(View.VISIBLE);
                } else {
                    descricaoInput.setVisibility(View.GONE);
                    especialidadesLabel.setVisibility(View.GONE);
                    especialidadesLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button cadastroBtn = findViewById(R.id.cadastro_btn);
        cadastroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCadastroRequest();
            }
        });

        ImageView backIcon = findViewById(R.id.back_icon);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_cadastro.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void sendCadastroRequest() {
        String nome = nameInput.getText().toString();
        String login = usernameInput.getText().toString();
        String senha = passwordInput.getText().toString();
        String descricao = descricaoInput.getText().toString();
        int tipoUsuarioId = tipoUsuarioSpinner.getSelectedItemPosition();

        List<Integer> especialidades = new ArrayList<>();
        if (desenvolvedor.isChecked()) especialidades.add(1);
        if (design.isChecked()) especialidades.add(2);
        if (java.isChecked()) especialidades.add(3);
        if (javascript.isChecked()) especialidades.add(4);
        if (springBoot.isChecked()) especialidades.add(5);
        if (react.isChecked()) especialidades.add(6);
        if (vueJs.isChecked()) especialidades.add(7);
        if (angular.isChecked()) especialidades.add(8);
        if (html.isChecked()) especialidades.add(9);
        if (css.isChecked()) especialidades.add(10);

        UsuarioCreateRequest request = new UsuarioCreateRequest();
        request.setNome(nome);
        request.setLogin(login);
        request.setSenha(senha);
        request.setTipoUsuarioId(tipoUsuarioId);
        request.setDescricao(descricao);
        request.setEspecialidades(especialidades);

        Log.d("Payload", new Gson().toJson(request));

        usuarioApi.createUsuario(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(activity_cadastro.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(activity_cadastro.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("API", "Erro ao cadastrar: " + response.code());
                    Toast.makeText(activity_cadastro.this, "Erro ao cadastrar usuário.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API", "Falha na comunicação: ", t);
                Toast.makeText(activity_cadastro.this, "Falha na comunicação: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
