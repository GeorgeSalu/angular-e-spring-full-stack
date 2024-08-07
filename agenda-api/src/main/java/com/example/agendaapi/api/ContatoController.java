package com.example.agendaapi.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.agendaapi.model.Contato;
import com.example.agendaapi.repository.ContatoRepository;

@RestController
@RequestMapping("/api/contatos")
@CrossOrigin("*")
public class ContatoController {

	private ContatoRepository repository;

	public ContatoController(ContatoRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contato save(@RequestBody Contato contato) {
		return repository.save(contato);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		repository.deleteById(id);
	}
	
	@GetMapping
	public List<Contato> list() {
		return repository.findAll();
	}
	
	@PatchMapping("{id}/favoritar")
	public void favorite(@PathVariable Integer id) {
		Optional<Contato> contato = repository.findById(id);
		contato.ifPresent(c -> {
			boolean favorito = c.getFavorito() == Boolean.TRUE;
			c.setFavorito(!favorito);
			repository.save(c);
		});
	}
	
	@PutMapping("{id}/foto")
	public byte[] addPhoto(@PathVariable Integer id, @RequestParam("foto") Part arquivo) {
		Optional<Contato> contato = repository.findById(id);
		return contato.map( c -> {
			try {
				InputStream is = arquivo.getInputStream();
				byte[] bytes = new byte[(int) arquivo.getSize()];
				IOUtils.readFully(is, bytes);
				c.setFoto(bytes);
				repository.save(c);
				is.close();
				return bytes;
			} catch (IOException e) {
				return null;
			}
		}).orElse(null);
	}
	
}


