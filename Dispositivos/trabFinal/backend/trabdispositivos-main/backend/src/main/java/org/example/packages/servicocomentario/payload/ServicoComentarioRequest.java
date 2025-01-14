package org.example.packages.servicocomentario.payload;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServicoComentarioRequest {
    private Integer usuarioId;
    private Integer servicoId;
    private String comentario;
    private BigDecimal orcamento;
    private String comentarioOrcamento;
}
