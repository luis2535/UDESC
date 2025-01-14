package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import negocios.Sistema;
import dados.*;
import excecoes.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "usuarios")
public class UsuarioController {
    
    

    @Autowired
    private Sistema sistema;
    
    @PostMapping(path = "/usuario")
//    @PutMapping(path = "/usuario")
    @ResponseBody
    public void cadastrarUsuario(@RequestBody Usuario usuario) throws SelectException, InsertException {
    	System.out.println(usuario);
        sistema.insereUsuario(usuario);
    }
    
    @GetMapping("/{cpf}")
    public Usuario buscarUsuario(@PathVariable String cpf) {
        try {
            return sistema.buscaUsuario(cpf);
        } catch (SelectException e) {
            // Tratar exceção de busca
            return null;
        }
    }
    
    @PutMapping("/{cpf}")
    public void atualizarUsuario(@PathVariable String cpf, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioExistente = sistema.buscaUsuario(cpf);
            if (usuarioExistente != null) {
                usuario.setCpf(cpf);
                sistema.atualizaUsuario(usuario);
            } else {
                // Retornar resposta de usuário não encontrado
            }
        } catch (SelectException | UpdateException e) {
            // Tratar exceção de busca/atualização
        }
    }
    
    @DeleteMapping("/{cpf}")
    public void deletarUsuario(@PathVariable String cpf) throws SelectException {
        try {
            Usuario usuarioExistente = sistema.buscaUsuario(cpf);
            if (usuarioExistente != null) {
                sistema.deletaUsuario(usuarioExistente);
            } else {
                // Retornar resposta de usuário não encontrado
            }
        } catch (DeleteException e) {
            // Tratar exceção de exclusão
        }
    }
    

}
