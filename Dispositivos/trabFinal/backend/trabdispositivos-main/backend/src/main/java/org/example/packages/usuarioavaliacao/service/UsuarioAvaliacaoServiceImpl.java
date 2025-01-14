package org.example.packages.usuarioavaliacao.service;

import lombok.AllArgsConstructor;
import org.example.packages.usuario.Usuario;
import org.example.packages.usuario.service.UsuarioService;
import org.example.packages.usuarioavaliacao.UsuarioAvaliacao;
import org.example.packages.usuarioavaliacao.UsuarioAvaliacaoRepository;
import org.example.packages.usuarioavaliacao.payload.UsuarioAvaliacaoRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioAvaliacaoServiceImpl implements UsuarioAvaliacaoService {
    private UsuarioService usuarioService;
    private UsuarioAvaliacaoRepository usuarioAvaliacaoRepository;

    @Override
    public void create(UsuarioAvaliacaoRequest usuarioAvaliacaoRequest) {
        UsuarioAvaliacao usuarioAvaliacao = new UsuarioAvaliacao();

        Usuario usuario = usuarioService.findById(usuarioAvaliacaoRequest.getUsuarioId());
        Usuario usuarioCriador = usuarioService.findById(usuarioAvaliacaoRequest.getUsuarioCriadorId());

        usuarioAvaliacao.setUsuario(usuario);
        usuarioAvaliacao.setNota(usuarioAvaliacao.getNota());
        usuarioAvaliacao.setUsuarioCriador(usuarioCriador);

        usuarioAvaliacaoRepository.save(usuarioAvaliacao);
    }

    public List<UsuarioAvaliacao> findByUsuario(Integer usuarioId)  {
        Usuario usuario = usuarioService.findById(usuarioId);

        return usuarioAvaliacaoRepository.findByUsuario(usuario);
    }
}
