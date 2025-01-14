package com.example.trabfinal.model;

import java.math.BigDecimal;

public class ServicoUsuarioRequest {
    private Integer usuarioId;
    private Integer servicoId;
    private BigDecimal valor;

    private Servico servico;
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
}
