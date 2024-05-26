package com.example.clientes.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.clientes.model.entity.Cliente;
import com.example.clientes.model.entity.ServicoPrestado;
import com.example.clientes.model.repository.ClienteRepository;
import com.example.clientes.model.repository.ServicoPrestadoRepository;
import com.example.clientes.rest.dto.ServicoPrestadoDTO;
import com.example.clientes.util.BigDecimalConverter;

@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {

	private ServicoPrestadoRepository servicoPrestadoRepository;
	private ClienteRepository clienteRepository;
	private BigDecimalConverter bigDecimalConverter;

	public ServicoPrestadoController(ClienteRepository clienteRepository,ServicoPrestadoRepository servicoPrestadoRepository,
			BigDecimalConverter bigDecimalConverter) {
		this.clienteRepository = clienteRepository;
		this.servicoPrestadoRepository = servicoPrestadoRepository;
		this.bigDecimalConverter = bigDecimalConverter;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		Integer idCliente = dto.getIdCliente();
		
		Cliente cliente = clienteRepository.findById(idCliente)
						 .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente"));
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));
		
		return servicoPrestadoRepository.save(servicoPrestado);
	}
	
	@GetMapping
	public List<ServicoPrestado> pesquisar(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
										   @RequestParam(value = "mes", required = false) Integer mes) {
		
		return servicoPrestadoRepository.findByNomeClienteAndMes("%"+nome+"%", mes);
		
	}
	
}



