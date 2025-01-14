package com.example.trabfinal.model;

import java.math.BigDecimal;
import java.util.List;

public class ServicoCreateRequest {
    private String descricao;
    private List<Integer> especialidades;
    private BigDecimal valor;
    private String valorDescricao;
    private Integer usuarioId;

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

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
