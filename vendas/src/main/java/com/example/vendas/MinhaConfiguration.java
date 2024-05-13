package com.example.vendas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinhaConfiguration {

	@Bean(name = "applicationName")
	public String applicationName() {
		return "sistema de vendas";
	}
	
}
