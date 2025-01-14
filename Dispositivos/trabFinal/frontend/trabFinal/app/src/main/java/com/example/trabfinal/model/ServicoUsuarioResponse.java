package com.example.trabfinal.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;

public class ServicoUsuarioResponse implements Serializable {

    @SerializedName("usuarioId")
    private Integer usuarioId;

    @SerializedName("servicoId")
    private Integer servicoId;

    @SerializedName("valor")
    private BigDecimal valor;
    @SerializedName("servico")
    private Servico servico;
    // Getters e Setters
    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getServicoId() {
        return servicoId;
    }

    public void setServicoId(Integer servicoId) {
        this.servicoId = servicoId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Servico getServico(){
        return servico;
    }

    public void setServico(Servico servico){
        this.servico = servico;
    }
}
