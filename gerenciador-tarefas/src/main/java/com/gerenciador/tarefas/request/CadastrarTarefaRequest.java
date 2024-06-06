package com.gerenciador.tarefas.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarTarefaRequest {

	@NotBlank(message = "{cadastrar.tarefa.request.titulo}")
	private String titulo;
	
	@NotBlank(message = "{cadastrar.tarefa.request.descricao}")
	private String descricao;
	
	@NotBlank(message = "{cadastrar.tarefa.request.criador}")
	private Long criadorId;
	
	@NotBlank(message = "{cadastrar.tarefa.request.quantidadeHorasEstimadas}")
	private int quantidadeHorasEstimadas;
}
