package com.gerenciador.tarefas.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ObterTarefasPaginadasResponse {

	private int paginaAtual;
	private Long totalItems;
	private int totalPaginas;
	private List<ObterTarefasResponse> tarefas;
	
}
