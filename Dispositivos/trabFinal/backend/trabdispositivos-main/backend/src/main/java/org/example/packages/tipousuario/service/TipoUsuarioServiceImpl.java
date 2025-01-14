package org.example.packages.tipousuario.service;

import lombok.AllArgsConstructor;
import org.example.packages.tipousuario.TipoUsuario;
import org.example.packages.tipousuario.TipoUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TipoUsuarioServiceImpl implements TipoUsuarioService{
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Override
    public TipoUsuario findById(Integer id) {
        return tipoUsuarioRepository.findById(id).orElse(null);
    }
}
