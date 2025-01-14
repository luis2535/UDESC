package com.example.trabfinal.api;

import com.example.trabfinal.model.ServicoComentarioRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServicoComentarioApi {

    /**
     * Envia um comentário para um serviço específico.
     *
     * @param request Dados do comentário que será enviado.
     * @return Resposta da API, vazio em caso de sucesso.
     */
    @POST("servico_comentario")
    Call<Void> enviarComentario(@Body ServicoComentarioRequest request);
}
