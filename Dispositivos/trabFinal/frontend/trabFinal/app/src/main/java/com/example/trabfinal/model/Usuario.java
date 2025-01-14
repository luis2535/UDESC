package com.example.trabfinal.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Usuario implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("nome")
    private String nome;

    @SerializedName("descricao")
    private String descricao;

    @SerializedName("login")
    private String login;

    @SerializedName("senha")
    private String senha;

    @SerializedName("tipoUsuario")
    private TipoUsuario tipoUsuario;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
