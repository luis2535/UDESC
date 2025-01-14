package org.example.packages.servico.payload;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.especialidade.Especialidade;
import org.example.packages.servico.Servico;
import org.example.packages.servicocomentario.payload.ServicoComentarioResponse;

import java.util.List;

@Setter
@Getter
public class ServicoResponse {
    private Servico servico;
    private List<Especialidade> especialidade;
    private List<ServicoComentarioResponse> servicoComentarioResponse;
}
