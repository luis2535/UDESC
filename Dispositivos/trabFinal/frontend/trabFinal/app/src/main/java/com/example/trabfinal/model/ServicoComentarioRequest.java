package com.example.trabfinal.model;

import java.math.BigDecimal;

public class ServicoComentarioRequest {

    private Integer usuarioId;
    private Integer servicoId;
    private String comentario;
    private BigDecimal orcamento;
    private String comentarioOrcamento;

    public ServicoComentarioRequest() {
    }


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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public BigDecimal getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(BigDecimal orcamento) {
        this.orcamento = orcamento;
    }

    public String getComentarioOrcamento() {
        return comentarioOrcamento;
    }

    public void setComentarioOrcamento(String comentarioOrcamento) {
        this.comentarioOrcamento = comentarioOrcamento;
    }
}
