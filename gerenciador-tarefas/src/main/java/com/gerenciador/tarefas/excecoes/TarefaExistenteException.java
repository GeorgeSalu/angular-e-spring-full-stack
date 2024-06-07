package com.gerenciador.tarefas.excecoes;

public class TarefaExistenteException extends RuntimeException {

	private static final long serialVersionUID = -4719949800223995843L;

	public TarefaExistenteException() {
		super();
	}
	
	public TarefaExistenteException(String msg) {
		super(msg);
	}
	
}
