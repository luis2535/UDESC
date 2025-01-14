package org.example.packages.usuario.service;

import org.example.packages.usuario.Usuario;
import org.example.packages.usuario.payload.UsuarioCreateRequest;
import org.example.packages.usuario.payload.UsuarioResponse;

public interface UsuarioService {

    void create(UsuarioCreateRequest usuarioCreateRequest);

    UsuarioResponse usuario(String login, String senha);

    Usuario findById(Integer id);
}
