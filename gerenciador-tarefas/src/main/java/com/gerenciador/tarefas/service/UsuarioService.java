package com.gerenciador.tarefas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.repository.IRoleRepository;
import com.gerenciador.tarefas.repository.IUsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IRoleRepository iRoleRepository;
	
	public Usuario salvarUsuario(Usuario usuario) {
		
		usuario.setRoles(
				usuario.getRoles()
					.stream()
					.map(role -> iRoleRepository.findByNome(role.getNome()))
					.toList()
		);
		
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		return this.iUsuarioRepository.save(usuario);
	}
	
	public Usuario atualizarUsuario(Usuario usuario) {
		
		usuario.setRoles(
				usuario.getRoles()
					.stream()
					.map(role -> iRoleRepository.findByNome(role.getNome()))
					.toList()
		);
		
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		return this.iUsuarioRepository.save(usuario);
	}
	
	public void excluirUsuario(Usuario usuario) {
		this.iUsuarioRepository.deleteById(usuario.getId());
	}
	
	public List<Usuario> obterUsuarios() {
		return this.iUsuarioRepository.findAll();
	}
	
}
