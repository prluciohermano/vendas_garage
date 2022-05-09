package io.github.prluciohermano.rest.controller;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.prluciohermano.domain.entity.Cliente;
import io.github.prluciohermano.domain.repository.Clientes;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	private Clientes clientes;
	
	public ClienteController( Clientes clientes) {
		this.clientes = clientes;
	}
	
	@GetMapping(value = "{id}")
	@ResponseBody
	public Cliente getClienteById( @PathVariable Integer id){
		return clientes
				.findById(id)
				.orElseThrow( () ->
						new ResponseStatusException(HttpStatus.NOT_FOUND,
									"Cliente não encontrado"));
	}
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save( @RequestBody Cliente cliente ) {
		return clientes.save(cliente);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete( @PathVariable Integer id) {
		clientes.findById(id)
		.map( cliente -> {
			clientes.delete(cliente);
			return cliente;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Cliente não encontrado") );
	}
	
	@PutMapping("{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update( @PathVariable Integer id,
						@RequestBody Cliente cliente){
		clientes
				.findById(id)
				.map( clienteExistente -> {
					cliente.setId(clienteExistente.getId());
					clientes.save(cliente);
					return clienteExistente;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Cliente não encontrado") );
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping
	public List<Cliente> find(Cliente filtro){
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(
						ExampleMatcher.StringMatcher.CONTAINING);
		@SuppressWarnings("rawtypes")
		Example example = Example.of(filtro, matcher);
		return clientes.findAll(example);
		
	}
			
}
