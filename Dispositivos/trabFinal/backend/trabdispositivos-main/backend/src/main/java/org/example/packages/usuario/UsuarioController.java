package org.example.packages.usuario;

import lombok.AllArgsConstructor;
import org.example.packages.usuario.payload.UsuarioCreateRequest;
import org.example.packages.usuario.payload.UsuarioResponse;
import org.example.packages.usuario.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
@AllArgsConstructor
public class UsuarioController {
    private UsuarioService usuarioService;

    @PostMapping
    public void create(@RequestBody UsuarioCreateRequest usuarioCreateRequest) {
        usuarioService.create(usuarioCreateRequest);
    }

    @GetMapping
    public UsuarioResponse getUsuario(@RequestParam String login, @RequestParam String senha) {
        return usuarioService.usuario(login, senha);
    }
}
