package org.example.packages.servicousuario;

import org.example.packages.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoUsuarioRepository extends JpaRepository<ServicoUsuario, Integer> {

    List<ServicoUsuario> findByUsuario(Usuario usuario);
}
