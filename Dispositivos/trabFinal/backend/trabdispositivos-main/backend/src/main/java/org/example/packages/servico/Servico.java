package org.example.packages.servico;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;
    private BigDecimal valor;
    private String valorDescricao;

    @ManyToOne
    private Usuario usuario;
}
