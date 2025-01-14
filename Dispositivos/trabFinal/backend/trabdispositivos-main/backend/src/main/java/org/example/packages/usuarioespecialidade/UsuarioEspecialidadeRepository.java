package org.example.packages.usuarioespecialidade;

import org.example.packages.especialidade.Especialidade;
import org.example.packages.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioEspecialidadeRepository extends JpaRepository<UsuarioEspecialidade, Integer> {

    @Query("select ue.especialidade from UsuarioEspecialidade ue "
            + "where ue.usuario = :usuario")
    List<Especialidade> findEspecialidadeByUsuario(Usuario usuario);
}
