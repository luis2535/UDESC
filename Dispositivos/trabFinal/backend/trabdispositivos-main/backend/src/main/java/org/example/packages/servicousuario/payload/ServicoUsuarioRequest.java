package org.example.packages.servicousuario.payload;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServicoUsuarioRequest {
    private Integer usuarioId;
    private Integer servicoId;
    private BigDecimal valor;
}
