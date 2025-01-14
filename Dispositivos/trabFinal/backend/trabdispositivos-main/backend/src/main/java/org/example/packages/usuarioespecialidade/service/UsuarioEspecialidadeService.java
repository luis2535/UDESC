package org.example.packages.usuarioespecialidade.service;

import org.example.packages.especialidade.Especialidade;
import org.example.packages.usuario.Usuario;

import java.util.List;

public interface UsuarioEspecialidadeService {

    void create(Integer especialidadeId, Usuario usuario);

    List<Especialidade> findByUsuario(Usuario usuario);
}
