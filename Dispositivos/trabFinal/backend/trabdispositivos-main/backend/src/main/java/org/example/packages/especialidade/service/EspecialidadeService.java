package org.example.packages.especialidade.service;

import org.example.packages.especialidade.Especialidade;

import java.util.List;

public interface EspecialidadeService {
    Especialidade findById(Integer id);
    List<Especialidade> findAll();
}
