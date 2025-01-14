package org.example.packages.usuarioavaliacao;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.usuario.Usuario;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UsuarioAvaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Usuario usuarioCriador;

    private Integer nota;
}
