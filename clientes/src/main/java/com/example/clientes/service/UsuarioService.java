package com.example.clientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.clientes.model.entity.Usuario;
import com.example.clientes.model.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username)
							.orElseThrow(() -> new UsernameNotFoundException("Login nao encontrado"));
		
		return User.builder()
					.username(usuario.getUsername())
					.password(usuario.getPassword())
					.roles("USER")
					.build();
	}
	
}
