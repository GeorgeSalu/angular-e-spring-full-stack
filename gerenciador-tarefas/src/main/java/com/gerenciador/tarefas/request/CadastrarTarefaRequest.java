package com.gerenciador.tarefas.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarTarefaRequest {

	@NotBlank(message = "{cadastrar.tarefa.request.titulo}")
	private String titulo;
	private String descricao;
	private Long criadorId;
	private int quantidadeHorasEstimadas;
}
