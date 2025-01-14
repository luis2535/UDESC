package org.example.packages.usuario;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.tipousuario.TipoUsuario;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String descricao;
    private String login;
    private String senha;

    @ManyToOne
    private TipoUsuario tipoUsuario;
}
