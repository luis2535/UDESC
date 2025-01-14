package com.example.trabfinal.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://10.0.2.2:8080/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static UsuarioApi createUsuarioApiService() {
        return getRetrofitInstance().create(UsuarioApi.class);
    }


    public static ServicoApi createServicoApiService() {
        return getRetrofitInstance().create(ServicoApi.class);
    }


    public static ServicoComentarioApi createServicoComentarioApiService() {
        return getRetrofitInstance().create(ServicoComentarioApi.class);
    }

    public static ServicoUsuarioApi createServicoUsuarioApiService() {
        return retrofit.create(ServicoUsuarioApi.class);
    }


}
