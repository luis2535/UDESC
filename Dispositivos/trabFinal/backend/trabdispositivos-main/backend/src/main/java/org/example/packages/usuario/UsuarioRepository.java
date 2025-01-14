package org.example.packages.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findFirstByLoginAndSenha(String login, String senha);
}
