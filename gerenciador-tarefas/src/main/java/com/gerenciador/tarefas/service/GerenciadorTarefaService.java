package com.gerenciador.tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.excecoes.NaoPermitirExcluirException;
import com.gerenciador.tarefas.repository.IGerenciadortarefasRepository;
import com.gerenciador.tarefas.request.AtualizarTarefaResquest;
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
	
	public Tarefa atulizarTarefa(Long id,AtualizarTarefaResquest request) {
		
		Tarefa tarefa = this.gerenciadortarefasRepository.findById(id).get();
		
		tarefa.setQuantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas());
		tarefa.setStatus(request.getStatus());
		tarefa.setTitulo(request.getTitulo());
		tarefa.setDescricao(request.getDescricao());
		tarefa.setResponsavel(usuarioService.obterUsuarioPorId(request.getResponsavelId()).get());
		tarefa.setQuantidadeHorasRealizadas(request.getQuantidadeHorasRealizadas());
		
		this.gerenciadortarefasRepository.save(tarefa);
		
		return tarefa;
	}
	
	public void excluirTarefa(Long id) {
		
		Tarefa tarefa = this.gerenciadortarefasRepository.findById(id).get();
		
		if(!TarefaStatusEnum.CRIADA.equals(tarefa.getStatus())) {
			throw new NaoPermitirExcluirException();
		}
		
		this.gerenciadortarefasRepository.deleteById(id);
	}
	
}



