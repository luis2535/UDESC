package org.example.packages.usuarioavaliacao.service;

import org.example.packages.usuarioavaliacao.UsuarioAvaliacao;
import org.example.packages.usuarioavaliacao.payload.UsuarioAvaliacaoRequest;

import java.util.List;

public interface UsuarioAvaliacaoService {
    void create(UsuarioAvaliacaoRequest usuarioAvaliacaoRequest);

    List<UsuarioAvaliacao> findByUsuario(Integer usuarioId);
}
