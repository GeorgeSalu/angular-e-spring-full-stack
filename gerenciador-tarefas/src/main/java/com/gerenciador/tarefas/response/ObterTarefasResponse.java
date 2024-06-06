package com.gerenciador.tarefas.response;

import java.time.LocalTime;

import com.gerenciador.tarefas.status.TarefaStatusEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ObterTarefasResponse {

	private Long id;
	private String titulo;
	private String descricao;
	private TarefaStatusEnum status;
	private String responsavel;
	private String criador;
	private int quantidadeHorasEstimadas;
	private Integer quantidadeHorasRealizadas;
	private LocalTime dataCadastro;
	private LocalTime dataAtualizada;
	
}
