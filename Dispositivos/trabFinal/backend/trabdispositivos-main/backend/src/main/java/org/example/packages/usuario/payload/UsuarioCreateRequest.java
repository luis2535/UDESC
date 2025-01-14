package org.example.packages.usuario.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioCreateRequest {
    private String nome;
    private String login;
    private String senha;
    private Integer tipoUsuarioId;
    private String descricao;
    private List<Integer> especialidades;
}
