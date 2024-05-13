package com.example.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vendas.model.Cliente;
import com.example.vendas.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public void salvarCliente(Cliente cliente) {
		validarCliente(cliente);
		clienteRepository.persistir(cliente);
	}
	
	public void validarCliente(Cliente cliente) {
		// aplica validacaoes
	}
}
