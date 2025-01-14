package org.example.packages.servico.service;

import lombok.AllArgsConstructor;
import org.example.packages.servico.Servico;
import org.example.packages.servico.ServicoRepository;
import org.example.packages.servico.payload.ServicoCreateRequest;
import org.example.packages.servico.payload.ServicoResponse;
import org.example.packages.servicocomentario.ServicoComentario;
import org.example.packages.servicocomentario.payload.ServicoComentarioResponse;
import org.example.packages.servicocomentario.service.ServicoComentarioService;
import org.example.packages.servicoespecialidade.service.ServicoEspecialidadeService;
import org.example.packages.usuario.Usuario;
import org.example.packages.usuario.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServicoServiceImpl implements ServicoService {
    private ServicoRepository servicoRepository;
    private UsuarioService usuarioService;
    private ServicoEspecialidadeService servicoEspecialidadeService;
    private ServicoComentarioService servicoComentarioService;

    @Override
    public void create(ServicoCreateRequest servicoCreateRequest) {
        Servico servico = new Servico();

        Usuario usuario = usuarioService.findById(servicoCreateRequest.getUsuarioId());

        servico.setUsuario(usuario);
        servico.setDescricao(servicoCreateRequest.getDescricao());
        servico.setValor(servicoCreateRequest.getValor());
        servico.setValorDescricao(servicoCreateRequest.getValorDescricao());

        servico = servicoRepository.save(servico);


        for (Integer id : servicoCreateRequest.getEspecialidades()) {
            servicoEspecialidadeService.create(id, servico);
        }
    }

    @Override
    public Servico findById(Integer id) {
        return servicoRepository.findById(id).orElse(null);
    }

    @Override
    public List<ServicoResponse> findAll(List<Integer> especialidades) {
        List<Servico> servicos = servicoRepository.findServicosByEspecialidade(especialidades);
        List<ServicoResponse> servicoResponses = new ArrayList<>();

        for (Servico s : servicos) {
            ServicoResponse servicoResponse = new ServicoResponse();

            servicoResponse.setServico(s);
            servicoResponse.setEspecialidade(servicoEspecialidadeService.findByServico(s));

            List<ServicoComentario> servicoComentarios = servicoComentarioService.findServicoComentarioByServico(s);
            List<ServicoComentarioResponse> servicoComentarioResponses = new ArrayList<>();

            for (ServicoComentario sc : servicoComentarios) {
                ServicoComentarioResponse servicoComentarioResponse = new ServicoComentarioResponse();

                servicoComentarioResponse.setComentario(sc.getComentario());
                servicoComentarioResponse.setOrcamento(sc.getOrcamento());
                servicoComentarioResponse.setUsuario(sc.getUsuario());

                servicoComentarioResponses.add(servicoComentarioResponse);
            }

            servicoResponse.setServicoComentarioResponse(servicoComentarioResponses);
            servicoResponses.add(servicoResponse);
        }

        return servicoResponses;
    }
    @Override
    public List<ServicoResponse> findServicosByUsuarioId(Integer usuarioId) {
        List<Servico> servicos = servicoRepository.findServicosByUsuarioId(usuarioId);
        List<ServicoResponse> servicoResponses = new ArrayList<>();

        for (Servico s : servicos) {
            ServicoResponse servicoResponse = new ServicoResponse();

            servicoResponse.setServico(s);
            servicoResponse.setEspecialidade(servicoEspecialidadeService.findByServico(s));

            List<ServicoComentario> servicoComentarios = servicoComentarioService.findServicoComentarioByServico(s);
            List<ServicoComentarioResponse> servicoComentarioResponses = new ArrayList<>();

            for (ServicoComentario sc : servicoComentarios) {
                ServicoComentarioResponse servicoComentarioResponse = new ServicoComentarioResponse();

                servicoComentarioResponse.setComentario(sc.getComentario());
                servicoComentarioResponse.setOrcamento(sc.getOrcamento());
                servicoComentarioResponse.setComentarioOrcamento(sc.getComentarioOrcamento());
                servicoComentarioResponse.setUsuario(sc.getUsuario());

                servicoComentarioResponses.add(servicoComentarioResponse);
            }

            servicoResponse.setServicoComentarioResponse(servicoComentarioResponses);
            servicoResponses.add(servicoResponse);
        }

        return servicoResponses;
    }

    @Override
    public ServicoResponse findServicoById(Integer id) {
        Servico servico = findById(id);

        ServicoResponse servicoResponse = new ServicoResponse();

        servicoResponse.setServico(servico);
        servicoResponse.setEspecialidade(servicoEspecialidadeService.findByServico(servico));
        List<ServicoComentario> servicoComentarios = servicoComentarioService.findServicoComentarioByServico(servico);
        List<ServicoComentarioResponse> servicoComentarioResponses = new ArrayList<>();

        for (ServicoComentario sc : servicoComentarios) {
            ServicoComentarioResponse servicoComentarioResponse = new ServicoComentarioResponse();

            servicoComentarioResponse.setComentario(sc.getComentario());
            servicoComentarioResponse.setOrcamento(sc.getOrcamento());
            servicoComentarioResponse.setUsuario(sc.getUsuario());

            servicoComentarioResponses.add(servicoComentarioResponse);
        }

        servicoResponse.setServicoComentarioResponse(servicoComentarioResponses);

        return servicoResponse;
    }

}
