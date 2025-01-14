package org.example.packages.especialidade.service;

import lombok.AllArgsConstructor;
import org.example.packages.especialidade.Especialidade;
import org.example.packages.especialidade.EspecialidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EspecialidadeServiceImpl implements EspecialidadeService{
    private EspecialidadeRepository especialidadeRepository;

    @Override
    public Especialidade findById(Integer id) {
        return especialidadeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Especialidade> findAll() {
        return especialidadeRepository.findAll();
    }
}
