package com.gerenciador.tarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteApiController {

	@GetMapping("/teste-api")
	public String teste() {
		return "sucesso";
	}
	
	@GetMapping("/teste-bem-vindo")
	public String testeBemVindo(@RequestParam(name = "nome") String nome) {
		return "Ola "+nome+", seja bem vindo";
	}
	
}
