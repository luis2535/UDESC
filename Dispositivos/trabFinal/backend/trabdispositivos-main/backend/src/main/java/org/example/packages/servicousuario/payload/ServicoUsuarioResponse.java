package org.example.packages.servicousuario.payload;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.servico.Servico;

import java.math.BigDecimal;

@Getter
@Setter
public class ServicoUsuarioResponse {
    private Servico servico;
    private BigDecimal valor;
}
