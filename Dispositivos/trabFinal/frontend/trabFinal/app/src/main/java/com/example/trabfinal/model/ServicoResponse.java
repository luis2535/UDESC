package com.example.trabfinal.model;

import java.math.BigDecimal;
import java.util.List;

public class ServicoResponse {

    private Servico servico;
    private List<Especialidade> especialidade;
    private List<ServicoComentarioResponse> servicoComentarioResponse;

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public List<Especialidade> getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(List<Especialidade> especialidade) {
        this.especialidade = especialidade;
    }

    public List<ServicoComentarioResponse> getServicoComentarioResponse() {
        return servicoComentarioResponse;
    }

    public void setServicoComentarioResponse(List<ServicoComentarioResponse> servicoComentarioResponse) {
        this.servicoComentarioResponse = servicoComentarioResponse;
    }

    public String getEspecialidadeAsString() {
        if (especialidade == null || especialidade.isEmpty()) {
            return "Nenhuma especialidade";
        }
        StringBuilder builder = new StringBuilder();
        for (Especialidade esp : especialidade) {
            builder.append(esp.getNome()).append(", ");
        }
        return builder.substring(0, builder.length() - 2); // Remove última vírgula e espaço
    }
}
