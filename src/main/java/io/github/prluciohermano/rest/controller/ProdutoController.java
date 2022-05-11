package io.github.prluciohermano.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import io.github.prluciohermano.domain.entity.Produto;
import io.github.prluciohermano.domain.repository.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	private Produtos repository;

	public ProdutoController(Produtos repository) {
		this.repository = repository;
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Produto save( @RequestBody Produto produto) {
		return repository.save(produto);
	}

	@PutMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void update( @PathVariable Integer id, @RequestBody Produto produto ) {
		repository
			.findById(id)
			.map( p -> {
			produto.setId(p.getId());
			repository.save(produto);
			return produto;
		}).orElseThrow( () -> new ResponseStatusException(NOT_FOUND,
				"Cliente não encontrado") );
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		repository
			.findById(id)
			.map( p -> {
			repository.delete(p);
			return Void.TYPE;
		}).orElseThrow( () -> new ResponseStatusException(NOT_FOUND,
				"Cliente não encontrado") );
	}
	
	@GetMapping("{id}")
	public Produto getById(@PathVariable Integer id) {
		return repository
				.findById(id)
				.orElseThrow( () -> new ResponseStatusException(NOT_FOUND,
					"Cliente não encontrado") );
	}
	
	 @GetMapping
	 public ResponseEntity<List<Produto>> find(){
		 return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
	 }
	 
}
