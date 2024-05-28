package com.example.clientes.exception;

public class UsuarioCadastradoException extends RuntimeException {

	public UsuarioCadastradoException(String login) {
		super("usuario ja cadastrado para o login "+login);
	}
	
}
