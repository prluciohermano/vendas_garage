package io.github.prluciohermano.rest.controller;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import io.github.prluciohermano.domain.entity.Produto;
import io.github.prluciohermano.domain.repository.Clientes;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	private Clientes clientes;
	
	public ClienteController( Clientes clientes) {
		this.clientes = clientes;
	}
	
	@GetMapping("{id}")  /* ******* Buscar cliente por ID */
	@ResponseBody
	public Cliente getClienteById( @PathVariable Integer id){
		return clientes
				.findById(id)
				.orElseThrow( () ->
						new ResponseStatusException(HttpStatus.NOT_FOUND,
									"Cliente não encontrado"));
	}
		
	@PostMapping  /* **********************  Salvar Cliente */
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save( @RequestBody Cliente cliente ) {
		return clientes.save(cliente);
	}
	
	@DeleteMapping("{id}")  /* ************* Deletar Cliente */
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
	
	@PutMapping("{id}")  /* **************** Atualizar um Cliente */
	@ResponseBody
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update( @PathVariable Integer id, @RequestBody Cliente cliente){
		clientes
				.findById(id)
				.map( clienteExistente -> {
					cliente.setId(clienteExistente.getId());
					clientes.save(cliente);
					return clienteExistente;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Cliente não encontrado") );
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Cliente>> find(){
		 return ResponseEntity.status(HttpStatus.OK).body(clientes.findAll());
	 }
			
}
