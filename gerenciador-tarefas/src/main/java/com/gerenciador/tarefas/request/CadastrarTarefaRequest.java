package com.gerenciador.tarefas.request;

import com.gerenciador.tarefas.status.TarefaStatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarTarefaRequest {

	private String titulo;
	private String descricao;
	private TarefaStatusEnum status;
	private Long responsavelId;
	private Long criadorId;
	private int quantidadeHorasEstimadas;
}
