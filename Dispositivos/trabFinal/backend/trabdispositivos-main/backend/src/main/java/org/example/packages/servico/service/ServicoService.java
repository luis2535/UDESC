package org.example.packages.servico.service;

import org.example.packages.servico.Servico;
import org.example.packages.servico.payload.ServicoCreateRequest;
import org.example.packages.servico.payload.ServicoResponse;

import java.util.List;

public interface ServicoService {
    void create(ServicoCreateRequest servicoCreateRequest);

    Servico findById(Integer id);

    List<ServicoResponse> findAll(List<Integer> especialidades);

    ServicoResponse findServicoById(Integer id);

    List<ServicoResponse> findServicosByUsuarioId(Integer usuarioId);

}
