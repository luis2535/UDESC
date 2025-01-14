package com.example.trabfinal.api;

import com.example.trabfinal.model.ServicoUsuarioRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServicoUsuarioApi {

    @POST("servico_usuario")
    Call<Void> contratarServico(@Body ServicoUsuarioRequest request);
}
