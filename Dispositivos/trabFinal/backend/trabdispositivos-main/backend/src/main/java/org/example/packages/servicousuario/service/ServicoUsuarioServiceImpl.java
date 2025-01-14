package org.example.packages.servicousuario.service;

import lombok.AllArgsConstructor;
import org.example.packages.servico.Servico;
import org.example.packages.servico.ServicoRepository;
import org.example.packages.servico.service.ServicoService;
import org.example.packages.servicousuario.ServicoUsuario;
import org.example.packages.servicousuario.ServicoUsuarioRepository;
import org.example.packages.servicousuario.payload.ServicoUsuarioRequest;
import org.example.packages.usuario.Usuario;
import org.example.packages.usuario.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicoUsuarioServiceImpl implements ServicoUsuarioService {
    private ServicoService servicoService;
    private UsuarioService usuarioService;
    private ServicoUsuarioRepository servicoUsuarioRepository;


    public void create(ServicoUsuarioRequest servicoUsuarioRequest) {
        ServicoUsuario servicoUsuario = new ServicoUsuario();
        Servico servico = servicoService.findById(servicoUsuarioRequest.getServicoId());
        Usuario usuario = usuarioService.findById(servicoUsuarioRequest.getUsuarioId());

        servicoUsuario.setServico(servico);
        servicoUsuario.setUsuario(usuario);
        servicoUsuario.setValor(servicoUsuarioRequest.getValor());

        servicoUsuarioRepository.save(servicoUsuario);
    }

    @Override
    public List<ServicoUsuario> findByUsuario(Integer usuarioId) {
        Usuario usuario = usuarioService.findById(usuarioId);

        return servicoUsuarioRepository.findByUsuario(usuario);
    }
}
