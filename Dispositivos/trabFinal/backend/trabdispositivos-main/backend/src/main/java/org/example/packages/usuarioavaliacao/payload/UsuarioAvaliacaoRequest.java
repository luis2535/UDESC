package org.example.packages.usuarioavaliacao.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioAvaliacaoRequest {
    private Integer usuarioId;
    private Integer usuarioCriadorId;
    private Integer nota;
}
