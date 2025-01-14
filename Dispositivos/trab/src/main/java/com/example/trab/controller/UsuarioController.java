package com.example.trab.controller;

import java.util.List;

import com.example.trab.dados.Usuario;
import com.example.trab.excecoes.DeleteException;
import com.example.trab.excecoes.InsertException;
import com.example.trab.excecoes.SelectException;
import com.example.trab.excecoes.UpdateException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.trab.negocios.Sistema;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UsuarioController {

	private final Sistema sistema;
	
	UsuarioController(Sistema sistema){
		this.sistema = sistema;
	}
	
	@GetMapping("/{cpf}")
	public Usuario selecionarUsuario(@PathVariable String cpf) throws SelectException {
		return sistema.buscaUsuario(cpf);
	}
	
	@PostMapping("/usuario")
	public void adicionaUsuario(@RequestBody Usuario usuario) throws SelectException, InsertException {
		sistema.insereUsuario(usuario);
	}
	@PutMapping("/usuario")
	public void atualizaUsuario(@RequestBody Usuario usuario) throws UpdateException {
		sistema.atualizaUsuario(usuario);	
	}
	@DeleteMapping("/usuario")
	public void deletaUsuario(@RequestBody Usuario usuario) throws DeleteException {
		sistema.deletaUsuario(usuario);
	}
	@GetMapping("/usuarios")
	public List<Usuario> selecionaUsuarios() throws SelectException{
		return sistema.buscaUsuarios();		
	}
	
	
}