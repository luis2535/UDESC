package org.example.packages.servicocomentario;

import lombok.AllArgsConstructor;
import org.example.packages.servico.Servico;
import org.example.packages.servico.service.ServicoService;
import org.example.packages.servicocomentario.payload.ServicoComentarioRequest;
import org.example.packages.servicocomentario.service.ServicoComentarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("servico_comentario")
@AllArgsConstructor
public class ServicoComentarioController {
    ServicoComentarioService servicoComentarioService;
    ServicoService servicoService;

    @PostMapping
    void create(@RequestBody ServicoComentarioRequest servicoComentarioRequest) {
        servicoComentarioService.create(servicoComentarioRequest);
    }
}
