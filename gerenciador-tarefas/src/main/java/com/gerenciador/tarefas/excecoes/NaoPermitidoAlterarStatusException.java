package com.gerenciador.tarefas.excecoes;

public class NaoPermitidoAlterarStatusException extends RuntimeException {

	public NaoPermitidoAlterarStatusException() {
		super();
	}
	
	public NaoPermitidoAlterarStatusException(String msg) {
		super(msg);
	}
	
}
