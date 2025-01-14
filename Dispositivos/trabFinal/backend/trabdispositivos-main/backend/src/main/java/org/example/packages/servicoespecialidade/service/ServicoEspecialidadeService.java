package org.example.packages.servicoespecialidade.service;

import org.example.packages.especialidade.Especialidade;
import org.example.packages.servico.Servico;

import java.util.List;

public interface ServicoEspecialidadeService {

    void create(Integer especialidades, Servico servico);

    List<Especialidade> findByServico(Servico servico);
}
