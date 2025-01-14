package org.example.packages.servicocomentario;

import org.example.packages.servico.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicoComentarioRepository extends JpaRepository<ServicoComentario, Integer> {

    List<ServicoComentario> findByServico(Servico servico);
}
