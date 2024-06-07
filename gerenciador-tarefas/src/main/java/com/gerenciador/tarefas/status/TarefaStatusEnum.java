package com.gerenciador.tarefas.status;

/**
 * 
 * regras
 * 1 - não permitir deletar tarefa se o status for diferente de CRIADA
 * 2 - não deixar a tarefa para FINALIZADA se a mesma estiver em CRIADA
 * 3 - nao deixar mover a tarefa para FINALIZADA  se a mesma estiver em BLOQUEADA
 * 4 - uma vez finalizada a tarefa nao pode mudar de status e nem ser deletada
 * 5 - se uma tarefa tiver a mesma descricao ou titulo informar que existe uma criada
 *
 */
public enum TarefaStatusEnum {
	CRIADA, PROGRESSO, BLOQUEADA, FINALIZADA
}
