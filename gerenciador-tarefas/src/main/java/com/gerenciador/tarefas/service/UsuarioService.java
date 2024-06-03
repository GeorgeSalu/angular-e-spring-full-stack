package com.gerenciador.tarefas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.repository.IUsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	
	public Usuario salvarUsuario(Usuario usuario) {
		return this.iUsuarioRepository.save(usuario);
	}
	
	public Usuario atualizarUsuario(Usuario usuario) {
		return this.iUsuarioRepository.save(usuario);
	}
	
	public void excluirUsuario(Usuario usuario) {
		this.iUsuarioRepository.deleteById(usuario.getId());
	}
	
	public List<Usuario> obterUsuarios() {
		return this.iUsuarioRepository.findAll();
	}
	
}
