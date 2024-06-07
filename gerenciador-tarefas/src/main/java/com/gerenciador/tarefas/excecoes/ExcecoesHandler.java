package com.gerenciador.tarefas.excecoes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gerenciador.tarefas.response.ErrorResponse;

@ControllerAdvice
public class ExcecoesHandler {

	@ExceptionHandler(NaoPermitirExcluirException.class)
	public ResponseEntity<ErrorResponse> naoPermitirExcluirExceptionHandler() {
		
		Map<String, String> response = new HashMap<>();
		response.put("codigo", ErrosEnum.NAO_PERMITIDO_EXCLUIR.toString());
		response.put("mensagem", "não é permitido excluir uma tarefa com status diferente de criada");
		
		ErrorResponse errorResponse = ErrorResponse.builder()
			.status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
			.errors(Collections.singletonList(response))
			.build();
		
		return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
}
