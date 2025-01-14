package com.example.trabfinal.api;

import com.example.trabfinal.model.UsuarioCreateRequest;
import com.example.trabfinal.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UsuarioApi {
    @POST("usuario")
    Call<Void> createUsuario(@Body UsuarioCreateRequest request);

    @GET("/usuario")
    Call<UsuarioResponse> autenticarUsuario(
            @Query("login") String login,
            @Query("senha") String senha
    );
}
