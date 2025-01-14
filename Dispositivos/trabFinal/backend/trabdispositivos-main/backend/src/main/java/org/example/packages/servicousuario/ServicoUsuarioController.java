package org.example.packages.servicousuario;

import lombok.AllArgsConstructor;
import org.example.packages.servicousuario.payload.ServicoUsuarioRequest;
import org.example.packages.servicousuario.service.ServicoUsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("servico_usuario")
@AllArgsConstructor
public class ServicoUsuarioController {
    private ServicoUsuarioService servicoUsuarioService;

    @PostMapping
    public void create(@RequestBody ServicoUsuarioRequest servicoUsuarioRequest) {
        servicoUsuarioService.create(servicoUsuarioRequest);
    }

    @GetMapping
    public List<ServicoUsuario> getByUsuario(@RequestParam Integer usuarioId) {
        return servicoUsuarioService.findByUsuario(usuarioId);
    }
}
