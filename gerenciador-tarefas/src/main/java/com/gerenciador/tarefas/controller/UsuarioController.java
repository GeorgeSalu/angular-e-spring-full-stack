package com.gerenciador.tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<String> salvar(@RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
		
		return new ResponseEntity<>("novo usuario criado "+usuarioSalvo.getUsername(), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<String> atualizarUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioAtualizado = usuarioService.atualizarUsuario(usuario);
		
		return new ResponseEntity<>("usuario atualizado "+usuarioAtualizado.getUsername(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> obtemUsuarios() {
		return new ResponseEntity<>(usuarioService.obterUsuarios(), HttpStatus.OK);
	}
	
	@DeleteMapping
	public void excluirUsuario(@RequestBody Usuario usuario) {
		usuarioService.excluirUsuario(usuario);
	}
	
}
