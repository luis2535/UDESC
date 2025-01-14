package org.example.packages.usuario.payload;

import lombok.Getter;
import lombok.Setter;
import org.example.packages.especialidade.Especialidade;
import org.example.packages.servicousuario.ServicoUsuario;
import org.example.packages.servicousuario.payload.ServicoUsuarioResponse;
import org.example.packages.usuario.Usuario;
import org.example.packages.usuarioespecialidade.UsuarioEspecialidade;

import java.util.List;

@Getter
@Setter
public class UsuarioResponse {
    Usuario usuario;
    List<Especialidade> especialidades;
    List<ServicoUsuarioResponse> servicoUsuarioResponses;
}
