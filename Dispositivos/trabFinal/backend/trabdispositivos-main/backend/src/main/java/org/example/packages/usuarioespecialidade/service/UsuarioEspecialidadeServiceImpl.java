package org.example.packages.usuarioespecialidade.service;

import lombok.AllArgsConstructor;
import org.example.packages.especialidade.Especialidade;
import org.example.packages.especialidade.service.EspecialidadeService;
import org.example.packages.usuario.Usuario;
import org.example.packages.usuarioespecialidade.UsuarioEspecialidade;
import org.example.packages.usuarioespecialidade.UsuarioEspecialidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioEspecialidadeServiceImpl implements UsuarioEspecialidadeService {
    private EspecialidadeService especialidadeService;
    private UsuarioEspecialidadeRepository usuarioEspecialidadeRepository;

    @Override
    public void create(Integer especialidadeId, Usuario usuario) {
        Especialidade especialidade = especialidadeService.findById(especialidadeId);

        UsuarioEspecialidade usuarioEspecialidade = new UsuarioEspecialidade();

        usuarioEspecialidade.setEspecialidade(especialidade);
        usuarioEspecialidade.setUsuario(usuario);

        usuarioEspecialidadeRepository.save(usuarioEspecialidade);
    }

    @Override
    public List<Especialidade> findByUsuario(Usuario usuario) {
        return usuarioEspecialidadeRepository.findEspecialidadeByUsuario(usuario);
    }
}
