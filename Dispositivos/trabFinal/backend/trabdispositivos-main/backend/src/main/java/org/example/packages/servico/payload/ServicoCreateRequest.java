package org.example.packages.servico.payload;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.especialidade.Especialidade;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ServicoCreateRequest {
    private String descricao;
    private List<Integer> especialidades;
    private BigDecimal valor;
    private String valorDescricao;
    private Integer usuarioId;
}
