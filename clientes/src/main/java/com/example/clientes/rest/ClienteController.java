package com.example.clientes.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.clientes.model.entity.Cliente;
import com.example.clientes.model.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	private ClienteRepository repository;

	@Autowired
	public ClienteController(ClienteRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public List<Cliente> obterTodos() {
		return repository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		return repository.save(cliente);
	}
	
	@GetMapping("{id}")
	public Cliente acharPorId(@PathVariable Integer id) {
		return repository.findById(id)
						 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		repository.findById(id)
				  .map(cliente -> {
					  repository.delete(cliente);
					  return Void.TYPE;
				  })
		 		  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id,@RequestBody Cliente clienteAtualizado) {
		repository.findById(id)
				  .map(cliente -> {
					  
					  cliente.setNome(clienteAtualizado.getNome());
					  cliente.setCpf(clienteAtualizado.getCpf());
					  
					  return repository.save(cliente);
				  })
				  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	
}


