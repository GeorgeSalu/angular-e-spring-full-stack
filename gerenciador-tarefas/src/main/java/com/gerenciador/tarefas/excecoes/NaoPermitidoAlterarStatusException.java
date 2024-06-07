package com.gerenciador.tarefas.excecoes;

public class NaoPermitidoAlterarStatusException extends RuntimeException {

	private static final long serialVersionUID = -5136954336402120001L;

	public NaoPermitidoAlterarStatusException() {
		super();
	}
	
	public NaoPermitidoAlterarStatusException(String msg) {
		super(msg);
	}
	
}
