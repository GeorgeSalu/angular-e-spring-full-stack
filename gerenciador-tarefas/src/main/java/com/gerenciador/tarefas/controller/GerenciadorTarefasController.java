package com.gerenciador.tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.request.AtualizarTarefaResquest;
import com.gerenciador.tarefas.request.CadastrarTarefaRequest;
import com.gerenciador.tarefas.response.AtualizarTarefaResponse;
import com.gerenciador.tarefas.response.CadastrarTarefaResponse;
import com.gerenciador.tarefas.response.ObterTarefasPaginadasResponse;
import com.gerenciador.tarefas.response.ObterTarefasResponse;
import com.gerenciador.tarefas.service.GerenciadorTarefaService;

@RestController
@RequestMapping("/gerenciador-tarefas")
public class GerenciadorTarefasController {

	@Autowired
	private GerenciadorTarefaService gerenciadorTarefaService;
	
	@PostMapping
	public ResponseEntity<CadastrarTarefaResponse> salvartarefa(@RequestBody CadastrarTarefaRequest request) {
		
		Tarefa tarefaSalva = gerenciadorTarefaService.salvarTarefa(request);
		
		CadastrarTarefaResponse response = CadastrarTarefaResponse.builder()
			.id(tarefaSalva.getId())
			.titulo(tarefaSalva.getTitulo())
			.descricao(tarefaSalva.getDescricao())
			.criador(tarefaSalva.getCriador().getUsername())
			.build();
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<ObterTarefasPaginadasResponse> obterTarefas(@RequestParam(required = false) String titulo,
																   @RequestParam(defaultValue = "0") int page,
																   @RequestParam(defaultValue = "3") int size) {
		
		
		Page<Tarefa> tarefasPaginadas = null;
		
		if(titulo == null) {
			tarefasPaginadas = this.gerenciadorTarefaService.obtemTodasTarefas(PageRequest.of(page, size));
		} else {
			tarefasPaginadas = this.gerenciadorTarefaService.obtemTarefasPorTitulo(titulo, PageRequest.of(page, size));
		}
		
		List<ObterTarefasResponse> tarefas = tarefasPaginadas.getContent().stream()
			.map( tarefa -> {
				return ObterTarefasResponse.builder()
					.id(tarefa.getId())
					.titulo(tarefa.getTitulo())
					.descricao(tarefa.getDescricao())
					.responsavel(tarefa.getResponsavel() != null ? tarefa.getResponsavel().getUsername() : "NAO_ATRIBUIDA")
					.criador(tarefa.getCriador().getUsername())
					.status(tarefa.getStatus())
					.quantidadeHorasEstimadas(tarefa.getQuantidadeHorasEstimadas())
					.quantidadeHorasRealizadas(tarefa.getQuantidadeHorasRealizadas())
					.dataCadastro(tarefa.getDataCadastro())
					.dataAtualizada(tarefa.getDataAtualizacao())
					.build();
			})
			.toList();
		
		ObterTarefasPaginadasResponse response = ObterTarefasPaginadasResponse.builder()
			.paginaAtual(tarefasPaginadas.getNumber())
			.totalPaginas(tarefasPaginadas.getTotalPages())
			.totalItems(tarefasPaginadas.getTotalElements())
			.tarefas(tarefas)
			.build();
			
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AtualizarTarefaResponse> atualizarTarefa(@PathVariable Long id,@RequestBody AtualizarTarefaResquest request) {
		
		Tarefa tarefaAtualizada = gerenciadorTarefaService.atulizarTarefa(id, request);
		
		AtualizarTarefaResponse response = AtualizarTarefaResponse.builder()
			.id(tarefaAtualizada.getId())
			.titulo(tarefaAtualizada.getTitulo())
			.descricao(tarefaAtualizada.getDescricao())
			.criador(tarefaAtualizada.getCriador().getUsername())
			.quantidadeHorasEstimadas(tarefaAtualizada.getQuantidadeHorasEstimadas())
			.quantidadeHorasRealizadas(tarefaAtualizada.getQuantidadeHorasRealizadas() != null ?  tarefaAtualizada.getQuantidadeHorasRealizadas() : null)
			.status(tarefaAtualizada.getStatus().toString())
			.responsavel(tarefaAtualizada.getResponsavel().getUsername())
			.build();
			
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void excluirTarefa(@PathVariable Long id) {
		gerenciadorTarefaService.excluirTarefa(id);
	}
	
}
