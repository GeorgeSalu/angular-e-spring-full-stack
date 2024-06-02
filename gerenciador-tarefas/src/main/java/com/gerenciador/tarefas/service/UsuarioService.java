package com.gerenciador.tarefas.service;

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
	
}
