package org.example.packages.servicocomentario.payload;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.usuario.Usuario;

import java.math.BigDecimal;

@Getter
@Setter
public class ServicoComentarioResponse {
    private String comentario;
    private BigDecimal orcamento;
    private Usuario usuario;
    private String comentarioOrcamento;
}
