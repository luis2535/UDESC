package org.example.packages.usuarioavaliacao;

import lombok.AllArgsConstructor;
import org.example.packages.usuarioavaliacao.payload.UsuarioAvaliacaoRequest;
import org.example.packages.usuarioavaliacao.service.UsuarioAvaliacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario_avaliacao")
@AllArgsConstructor
public class UsuarioAvaliacaoController {
    private UsuarioAvaliacaoService usuarioAvaliacaoService;

    @PostMapping
    public void create(@RequestBody UsuarioAvaliacaoRequest usuarioAvaliacaoRequest) {
        usuarioAvaliacaoService.create(usuarioAvaliacaoRequest);
    }


    @GetMapping
    public List<UsuarioAvaliacao> findByUsuario(@RequestParam Integer usuarioId) {
        return usuarioAvaliacaoService.findByUsuario(usuarioId);
    }
}
