package com.example.trabfinal.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Especialidade implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("nome")
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
