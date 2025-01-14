package org.example.packages.servico;

import lombok.AllArgsConstructor;
import org.example.packages.servico.payload.ServicoCreateRequest;
import org.example.packages.servico.payload.ServicoResponse;
import org.example.packages.servico.service.ServicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("servico")
@AllArgsConstructor
public class ServicoController {
    ServicoService servicoService;

    @PostMapping
    public void create(@RequestBody ServicoCreateRequest servicoCreateRequest) {
        servicoService.create(servicoCreateRequest);
    }

    @GetMapping
    public ServicoResponse findByFilter(@RequestParam Integer servicoId) {
        return servicoService.findServicoById(servicoId);
    }

    @GetMapping("/lista")
    public List<ServicoResponse> findAll(@RequestParam List<Integer> especialidadesId) {
        return servicoService.findAll(especialidadesId);
    }

    @GetMapping("/usuario")
    public List<ServicoResponse> findServicosByUsuario(@RequestParam Integer usuarioId) {
        return servicoService.findServicosByUsuarioId(usuarioId);
    }


}
