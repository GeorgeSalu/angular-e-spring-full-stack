package com.gerenciador.tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.request.CadastrarTarefaRequest;
import com.gerenciador.tarefas.service.GerenciadorTarefaService;

@RestController
@RequestMapping("/gerenciador-tarefas")
public class GerenciadorTarefasController {

	@Autowired
	private GerenciadorTarefaService gerenciadorTarefaService;
	
	@PostMapping
	public ResponseEntity<Tarefa> salvartarefa(@RequestBody CadastrarTarefaRequest request) {
		
		Tarefa tarefaSalva = gerenciadorTarefaService.salvarTarefa(request);
		return new ResponseEntity<>(tarefaSalva, HttpStatus.CREATED);
	}
	
}
