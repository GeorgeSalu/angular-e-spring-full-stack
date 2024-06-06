package com.gerenciador.tarefas.request;

import com.gerenciador.tarefas.status.TarefaStatusEnum;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarTarefaResquest {

	@NotBlank(message = "{alterar.tarefa.request.titulo}")
	private String titulo;
	private String descricao;
	private TarefaStatusEnum status;
	private Long responsavelId;
	private int quantidadeHorasEstimadas;
	private Integer quantidadeHorasRealizadas;
	
}
