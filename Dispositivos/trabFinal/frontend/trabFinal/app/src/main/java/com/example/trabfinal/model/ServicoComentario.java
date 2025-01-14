package com.example.trabfinal.model;

import java.math.BigDecimal;

public class ServicoComentario {
    private Integer id;
    private String comentario;
    private BigDecimal orcamento;
    private String comentarioOrcamento;
    private Usuario usuario;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
