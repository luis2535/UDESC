package org.example.packages.usuarioavaliacao;

import org.example.packages.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioAvaliacaoRepository extends JpaRepository<UsuarioAvaliacao, Integer> {


    List<UsuarioAvaliacao> findByUsuario(Usuario usuario);
}
