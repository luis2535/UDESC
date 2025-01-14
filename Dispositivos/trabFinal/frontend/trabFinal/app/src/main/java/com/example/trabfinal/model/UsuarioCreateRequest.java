package com.example.trabfinal.model;

import java.util.List;

public class UsuarioCreateRequest {
    private String nome;
    private String login;
    private String senha;
    private Integer tipoUsuarioId;
    private String descricao;
    private List<Integer> especialidades;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Integer getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(Integer tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Integer> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Integer> especialidades) {
        this.especialidades = especialidades;
    }
}
