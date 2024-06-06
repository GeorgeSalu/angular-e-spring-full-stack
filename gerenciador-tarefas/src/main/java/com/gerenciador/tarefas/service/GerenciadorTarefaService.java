package com.gerenciador.tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.repository.IGerenciadortarefasRepository;
import com.gerenciador.tarefas.request.CadastrarTarefaRequest;
import com.gerenciador.tarefas.status.TarefaStatusEnum;

@Service
public class GerenciadorTarefaService {

	@Autowired
	private IGerenciadortarefasRepository gerenciadortarefasRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Tarefa salvarTarefa(CadastrarTarefaRequest request) {
		
		Tarefa tarefa = Tarefa.builder()
							.quantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas())
							.status(TarefaStatusEnum.CRIADA)
							.titulo(request.getTitulo())
							.descricao(request.getDescricao())
							.criador(usuarioService.obterUsuarioPorId(request.getCriadorId()).get())
							.build();
							
		return gerenciadortarefasRepository.save(tarefa);
	}
	
	public Page<Tarefa> obtemTarefasPorTitulo(String titulo,Pageable pageable) {
		return this.gerenciadortarefasRepository.findByTituloContaining(titulo, pageable);
	}
	
	public Page<Tarefa> obtemTodasTarefas(Pageable pageable) {
		return this.gerenciadortarefasRepository.findAll(pageable);
	}
	
}
