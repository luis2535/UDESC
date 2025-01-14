package org.example.packages.especialidade;

import lombok.AllArgsConstructor;
import org.example.packages.especialidade.service.EspecialidadeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("especialidade")
@AllArgsConstructor
public class EspecialidadeController {
    private EspecialidadeService especialidadeService;

    @GetMapping
    public List<Especialidade> findAll() {
        return especialidadeService.findAll();

    }
}
