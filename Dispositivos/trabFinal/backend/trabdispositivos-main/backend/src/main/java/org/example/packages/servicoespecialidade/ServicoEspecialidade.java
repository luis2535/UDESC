package org.example.packages.servicoespecialidade;


import lombok.Getter;
import lombok.Setter;
import org.example.packages.especialidade.Especialidade;
import org.example.packages.servico.Servico;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ServicoEspecialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Servico servico;

    @ManyToOne
    private Especialidade especialidade;
}
