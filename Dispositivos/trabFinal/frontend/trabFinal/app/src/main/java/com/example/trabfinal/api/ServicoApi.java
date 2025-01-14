package com.example.trabfinal.api;

import com.example.trabfinal.model.ServicoCreateRequest;
import com.example.trabfinal.model.ServicoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;



public interface ServicoApi {

    @POST("servico")
    Call<Void> criarServico(@Body ServicoCreateRequest servicoCreateRequest);

    @GET("servico/lista")
    Call<List<ServicoResponse>> listarServicos(@Query("especialidadesId") List<Integer> especialidadesIds);

    @GET("servico")
    Call<ServicoResponse> findServicoById(@Query("servicoId") int servicoId);

    @GET("servico/usuario")
    Call<List<ServicoResponse>> listarServicosPorUsuario(@Query("usuarioId") int usuarioId);

}
