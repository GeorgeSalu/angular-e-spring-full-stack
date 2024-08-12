package com.gerenciador.tarefas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.repository.RoleRepository;
import com.gerenciador.tarefas.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioRepository iUsuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository iRoleRepository;
	
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
	
	public Optional<Usuario> obterUsuarioPorId(Long usuarioId) {
		return this.iUsuarioRepository.findById(usuarioId);
	}
	
}
