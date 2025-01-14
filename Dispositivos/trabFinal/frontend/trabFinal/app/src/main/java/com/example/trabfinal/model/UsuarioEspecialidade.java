package com.example.trabfinal.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class UsuarioEspecialidade implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("usuario")
    private Usuario usuario;

    @SerializedName("especialidade")
    private Especialidade especialidade;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}
