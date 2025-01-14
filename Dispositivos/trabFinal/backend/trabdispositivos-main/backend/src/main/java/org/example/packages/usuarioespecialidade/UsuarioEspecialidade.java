package org.example.packages.usuarioespecialidade;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.especialidade.Especialidade;
import org.example.packages.usuario.Usuario;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UsuarioEspecialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    Usuario usuario;

    @ManyToOne
    Especialidade especialidade;
}
