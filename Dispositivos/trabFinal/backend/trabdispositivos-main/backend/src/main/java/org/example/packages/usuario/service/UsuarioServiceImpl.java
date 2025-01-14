package org.example.packages.usuario.service;

import lombok.AllArgsConstructor;
import org.example.packages.especialidade.Especialidade;
import org.example.packages.especialidade.service.EspecialidadeService;
import org.example.packages.servicousuario.ServicoUsuario;
import org.example.packages.servicousuario.ServicoUsuarioRepository;
import org.example.packages.servicousuario.payload.ServicoUsuarioResponse;
import org.example.packages.servicousuario.service.ServicoUsuarioService;
import org.example.packages.tipousuario.TipoUsuario;
import org.example.packages.tipousuario.service.TipoUsuarioService;
import org.example.packages.usuario.Usuario;
import org.example.packages.usuario.UsuarioRepository;
import org.example.packages.usuario.payload.UsuarioCreateRequest;
import org.example.packages.usuario.payload.UsuarioResponse;
import org.example.packages.usuarioespecialidade.service.UsuarioEspecialidadeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private TipoUsuarioService tipoUsuarioService;
    private UsuarioEspecialidadeService usuarioEspecialidadeService;
    private UsuarioRepository usuarioRepository;
    private ServicoUsuarioRepository servicoUsuarioService;


    @Override
    public void create(UsuarioCreateRequest usuarioCreateRequest) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioCreateRequest.getNome());
        usuario.setSenha(usuarioCreateRequest.getSenha());
        usuario.setLogin(usuarioCreateRequest.getLogin());
        usuario.setDescricao(usuarioCreateRequest.getDescricao());

        TipoUsuario tipoUsuario = tipoUsuarioService.findById(usuarioCreateRequest.getTipoUsuarioId());
        usuario.setTipoUsuario(tipoUsuario);

        usuario = usuarioRepository.save(usuario);

        for (Integer especialidadeId : usuarioCreateRequest.getEspecialidades()) {
            usuarioEspecialidadeService.create(especialidadeId, usuario);
        }
    }

    @Override
    public UsuarioResponse usuario(String login, String senha) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        Usuario usuario = usuarioRepository.findFirstByLoginAndSenha(login, senha);

        usuarioResponse.setUsuario(usuario);
        usuarioResponse.setEspecialidades(usuarioEspecialidadeService.findByUsuario(usuario));

        List<ServicoUsuario> servicoUsuarios = servicoUsuarioService.findByUsuario(usuario);

        List<ServicoUsuarioResponse> servicoUsuarioResponses = new ArrayList<>();

        for (ServicoUsuario s : servicoUsuarios) {
            ServicoUsuarioResponse servicoUsuarioResponse = new ServicoUsuarioResponse();

            servicoUsuarioResponse.setServico(s.getServico());
            servicoUsuarioResponse.setValor(s.getValor());

            servicoUsuarioResponses.add(servicoUsuarioResponse);
        }

        usuarioResponse.setServicoUsuarioResponses(servicoUsuarioResponses);

        return usuarioResponse;
    }

    @Override
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
