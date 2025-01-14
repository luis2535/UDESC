package org.example.packages.servicocomentario.service;

import lombok.AllArgsConstructor;
import org.example.packages.servico.Servico;
import org.example.packages.servico.ServicoRepository;
import org.example.packages.servico.service.ServicoService;
import org.example.packages.servicocomentario.ServicoComentario;
import org.example.packages.servicocomentario.ServicoComentarioRepository;
import org.example.packages.servicocomentario.payload.ServicoComentarioRequest;
import org.example.packages.usuario.Usuario;
import org.example.packages.usuario.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicoComentarioServiceImpl implements ServicoComentarioService {
    ServicoComentarioRepository servicoComentarioRepository;
    ServicoRepository servicoRepository;
    UsuarioService usuarioService;

    @Override
    public List<ServicoComentario> findServicoComentarioByServico(Servico servico) {
        return servicoComentarioRepository.findByServico(servico);
    }

    @Override
    public void create(ServicoComentarioRequest servicoComentarioRequest) {
        ServicoComentario servicoComentario = new ServicoComentario();

        servicoComentario.setComentario(servicoComentarioRequest.getComentario());
        servicoComentario.setOrcamento(servicoComentarioRequest.getOrcamento());
        servicoComentario.setComentarioOrcamento(servicoComentarioRequest.getComentarioOrcamento());

        Usuario usuario = usuarioService.findById(servicoComentarioRequest.getUsuarioId());
        Servico servico = servicoRepository.findById(servicoComentarioRequest.getServicoId()).orElse(null);

        servicoComentario.setServico(servico);
        servicoComentario.setUsuario(usuario);

        servicoComentarioRepository.save(servicoComentario);
    }
}
