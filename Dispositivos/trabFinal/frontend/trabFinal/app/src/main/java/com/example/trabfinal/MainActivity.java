package com.example.trabfinal;

import android.content.Intent; // Import para o Intent
import android.os.Bundle;
import android.util.Log;
import android.view.View; // Import para o View
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView; // Import para o TextView
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trabfinal.api.ApiClient;
import com.example.trabfinal.api.UsuarioApi;
import com.example.trabfinal.model.UsuarioResponse;
import com.example.trabfinal.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput, passwordInput;
    private UsuarioApi usuarioApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);

        usuarioApi = ApiClient.createUsuarioApiService();

        Button loginButton = findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = usernameInput.getText().toString();
                String senha = passwordInput.getText().toString();

                if (login.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                } else {
                    autenticarUsuario(login, senha);
                }
            }
        });

        TextView cadastroTextView = findViewById(R.id.cadastro_text);
        cadastroTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_cadastro.class);
                startActivity(intent);
            }
        });
    }

    private void autenticarUsuario(String login, String senha) {
        usuarioApi.autenticarUsuario(login, senha).enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UsuarioResponse usuarioResponse = response.body();

                    SessionManager.getInstance().setUsuarioResponse(usuarioResponse);

                    Toast.makeText(MainActivity.this, "Bem-vindo, " + usuarioResponse.getUsuario().getNome() + "!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    int errorCode = response.code();
                    String mensagemErro;

                    if (errorCode == 401) {
                        mensagemErro = "Login ou senha inválidos.";
                    } else if (errorCode == 404) {
                        mensagemErro = "Usuário não encontrado.";
                    } else {
                        mensagemErro = "Erro ao processar a solicitação. Código: " + errorCode;
                    }

                    Toast.makeText(MainActivity.this, mensagemErro, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.e("API", "Erro ao autenticar usuário", t);
                Toast.makeText(MainActivity.this, "Erro ao conectar ao servidor. Tente novamente.", Toast.LENGTH_LONG).show();
            }
        });
    }


}
