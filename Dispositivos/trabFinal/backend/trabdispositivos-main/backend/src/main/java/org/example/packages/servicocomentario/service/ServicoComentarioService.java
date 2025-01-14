package org.example.packages.servicocomentario.service;

import org.example.packages.servico.Servico;
import org.example.packages.servicocomentario.ServicoComentario;
import org.example.packages.servicocomentario.payload.ServicoComentarioRequest;

import java.util.List;

public interface ServicoComentarioService {
    List<ServicoComentario> findServicoComentarioByServico(Servico servico);

    void create(ServicoComentarioRequest servicoComentarioRequest);
}
