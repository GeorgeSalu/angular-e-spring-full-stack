package com.gerenciador.tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.excecoes.NaoPermitidoAlterarStatusException;
import com.gerenciador.tarefas.excecoes.NaoPermitirExcluirException;
import com.gerenciador.tarefas.excecoes.TarefaExistenteException;
import com.gerenciador.tarefas.repository.GerenciadorTarefasRepository;
import com.gerenciador.tarefas.request.AtualizarTarefaResquest;
import com.gerenciador.tarefas.request.CadastrarTarefaRequest;
import com.gerenciador.tarefas.status.TarefaStatusEnum;

@Service
public class GerenciadorTarefaService {

	@Autowired
	private GerenciadorTarefasRepository gerenciadortarefasRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Tarefa salvarTarefa(CadastrarTarefaRequest request) {
		
		Tarefa tarefaValidacao = gerenciadortarefasRepository.findByTituloOrDescricao(request.getTitulo(), request.getDescricao());
		
		if(tarefaValidacao != null) {
			throw new TarefaExistenteException("ja existe uma tarefa com o mesmo titulo ou descricao");
		}
		
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
		return this.gerenciadortarefasRepository.findByTituloContainingOrderByDataAtualizacaoDesc(titulo, pageable);
	}
	
	public Page<Tarefa> obtemTodasTarefas(Pageable pageable) {
		return this.gerenciadortarefasRepository.findAllByOrderByDataAtualizacaoDesc(pageable);
	}
	
	public Tarefa atulizarTarefa(Long id,AtualizarTarefaResquest request) {
		
		Tarefa tarefa = this.gerenciadortarefasRepository.findById(id).get();
		
		if(tarefa.getStatus().equals(TarefaStatusEnum.FINALIZADA)) {
			throw new NaoPermitidoAlterarStatusException("nao permitido mover tarefa que esta finalizada");
		}
		
		if(tarefa.getStatus().equals(TarefaStatusEnum.CRIADA) && request.getStatus().equals(TarefaStatusEnum.FINALIZADA)) {
			throw new NaoPermitidoAlterarStatusException("nao permitido mover a tarefa para finalizada se a mesma estiver com o status de criada");
		}
		
		if(tarefa.getStatus().equals(TarefaStatusEnum.BLOQUEADA) && request.getStatus().equals(TarefaStatusEnum.FINALIZADA)) {
			throw new NaoPermitidoAlterarStatusException("nao permitido mover a tarefa para finalizada se a mesma estiver com o status de bloqueada");
		}
		
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



