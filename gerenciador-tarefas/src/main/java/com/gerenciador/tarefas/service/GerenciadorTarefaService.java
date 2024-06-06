package com.gerenciador.tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.repository.IGerenciadortarefasRepository;
import com.gerenciador.tarefas.request.CadastrarTarefaRequest;

@Service
public class GerenciadorTarefaService {

	@Autowired
	private IGerenciadortarefasRepository gerenciadortarefasRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Tarefa salvarTarefa(CadastrarTarefaRequest request) {
		
		Tarefa tarefa = Tarefa.builder()
							.quantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas())
							.status(request.getStatus())
							.titulo(request.getTitulo())
							.descricao(request.getDescricao())
							.responsavel(usuarioService.obterUsuarioPorId(request.getResponsavelId()).get())
							.criador(usuarioService.obterUsuarioPorId(request.getResponsavelId()).get())
							.build();
							
		return gerenciadortarefasRepository.save(tarefa);
	}
	
}
