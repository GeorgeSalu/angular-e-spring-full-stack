package com.gerenciador.tarefas.response;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {

	private String status;
	private List<Map<String, String>> errors;
	
}
