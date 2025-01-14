package org.example.packages.servicousuario;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.servico.Servico;
import org.example.packages.usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class ServicoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Servico servico;

    private BigDecimal valor;
}
