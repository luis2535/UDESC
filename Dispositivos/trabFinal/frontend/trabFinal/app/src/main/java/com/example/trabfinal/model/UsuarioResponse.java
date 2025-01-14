package com.example.trabfinal.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class UsuarioResponse implements Serializable {

    @SerializedName("usuario")
    private Usuario usuario;

    @SerializedName("especialidades")
    private List<Especialidade> especialidades;

    @SerializedName("servicoUsuarioResponses")
    private List<ServicoUsuarioResponse> servicoUsuarioResponses;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public List<ServicoUsuarioResponse> getServicoUsuarioResponses() {
        return servicoUsuarioResponses;
    }

    public void setServicoUsuarioResponses(List<ServicoUsuarioResponse> servicoUsuarioResponses) {
        this.servicoUsuarioResponses = servicoUsuarioResponses;
    }
}
