package com.example.trabfinal.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;

public class Servico implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("descricao")
    private String descricao;

    @SerializedName("valor")
    private BigDecimal valor;

    @SerializedName("valorDescricao")
    private String valorDescricao;

    @SerializedName("usuario")
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getValorDescricao() {
        return valorDescricao;
    }

    public void setValorDescricao(String valorDescricao) {
        this.valorDescricao = valorDescricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
