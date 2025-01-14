package org.example.packages.servicocomentario;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.servico.Servico;
import org.example.packages.usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class ServicoComentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comentario;
    private BigDecimal orcamento;
    private String comentarioOrcamento;

    @ManyToOne
    private Servico servico;

    @ManyToOne
    private Usuario usuario;

}
