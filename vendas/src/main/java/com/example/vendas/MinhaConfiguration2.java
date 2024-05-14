package com.example.vendas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class MinhaConfiguration2 {

	@Bean
	public CommandLineRunner executar() {
		return args -> {
			System.out.println("rodando a configuracao de desenvolvimento");
		};
	}
	
}
