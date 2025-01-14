package org.example.packages.servicoespecialidade.service;

import lombok.AllArgsConstructor;
import org.example.packages.especialidade.Especialidade;
import org.example.packages.especialidade.service.EspecialidadeService;
import org.example.packages.servico.Servico;
import org.example.packages.servicoespecialidade.ServicoEspecialidade;
import org.example.packages.servicoespecialidade.ServicoEspecialidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicoEspecialidadeServiceImpl implements ServicoEspecialidadeService {
    private ServicoEspecialidadeRepository servicoEspecialidadeRepository;
    private EspecialidadeService especialidadeService;

    @Override
    public void create(Integer especialidadeId, Servico servico) {
        Especialidade especialidade = especialidadeService.findById(especialidadeId);

        ServicoEspecialidade servicoEspecialidade = new ServicoEspecialidade();

        servicoEspecialidade.setEspecialidade(especialidade);
        servicoEspecialidade.setServico(servico);

        servicoEspecialidadeRepository.save(servicoEspecialidade);
    }

    @Override
    public List<Especialidade> findByServico(Servico servico) {
        return servicoEspecialidadeRepository.findEspecialidadeByServico(servico);
    }
}
