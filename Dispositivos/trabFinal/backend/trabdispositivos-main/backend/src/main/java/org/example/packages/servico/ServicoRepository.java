package org.example.packages.servico;

import org.example.packages.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    @Query("select distinct s from Servico s "
            + "left join ServicoEspecialidade se on se.servico = s "
            + "left join ServicoUsuario su on su.servico = s "
            + "where su is null "
            + "and se.especialidade.id in (:especialidadesId)")
    public List<Servico> findServicosByEspecialidade(List<Integer> especialidadesId);



    @Query("select s from Servico s where s.usuario.id = :usuarioId")
    List<Servico> findServicosByUsuarioId(Integer usuarioId);

}
