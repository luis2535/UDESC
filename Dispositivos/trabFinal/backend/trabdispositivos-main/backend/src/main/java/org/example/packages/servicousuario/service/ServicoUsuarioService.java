package org.example.packages.servicousuario.service;

import org.example.packages.servicousuario.ServicoUsuario;
import org.example.packages.servicousuario.payload.ServicoUsuarioRequest;

import java.util.List;

public interface ServicoUsuarioService {

    void create(ServicoUsuarioRequest servicoUsuarioRequest);

    List<ServicoUsuario> findByUsuario(Integer usuarioId);
}
