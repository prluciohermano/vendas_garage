package io.github.prluciohermano.rest.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.prluciohermano.domain.entity.Cliente;
import io.github.prluciohermano.domain.repository.Clientes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/clientes")
@Api("Api Clientes")

public class ClienteController {
	
	private Clientes clientes;
	
	public ClienteController( Clientes clientes) {
		this.clientes = clientes;
	}
	
	@GetMapping("{id}")  /* ***************************************** Buscar cliente por ID */
	@ApiOperation("Obter detalhes de um cliente puxando pelo ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cliente encontrado"),
		@ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado")
	})
	public Cliente getClienteById(
			@PathVariable
			@ApiParam("Id do Cliente") Integer id){
		return clientes
				.findById(id)
				.orElseThrow( () ->
						new ResponseStatusException(HttpStatus.NOT_FOUND,
									"Cliente não encontrado"));
	}
		
	@PostMapping  /* *****************************************************  Salvar Cliente */
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Cria um novo cliente")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public Cliente save( @RequestBody @Valid Cliente cliente ) {
		return clientes.save(cliente);
	}
	
	@DeleteMapping("{id}")  /* ********************************************* Deletar Cliente */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Deleta um cliente")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cliente deletado com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public void delete( @PathVariable Integer id) {
		clientes.findById(id)
		.map( cliente -> {
			clientes.delete(cliente);
			return cliente;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Cliente não encontrado") );
	}
	
	@PutMapping("{id}")  /* ************************************************ Atualizar um Cliente */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Altera os dados de um cliente")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cliente alterado com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public void update( @PathVariable Integer id, @RequestBody
												  @Valid Cliente cliente){
		clientes
				.findById(id)
				.map( clienteExistente -> {
					cliente.setId(clienteExistente.getId());
					clientes.save(cliente);
					return clienteExistente;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Cliente não encontrado") );
	}
	
	
	
	@GetMapping /************************************* Busca cliente por parâmetros */
	@ApiOperation("Busca um cliente por parâmetros")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cliente encontrado com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	    public List<Cliente> find( Cliente filtro ){
	        ExampleMatcher matcher = ExampleMatcher
	        		.matching().withIgnoreCase()
	                .withStringMatcher(
	                		ExampleMatcher.StringMatcher.CONTAINING );
	        
	        Example example = Example.of(filtro, matcher);
			return clientes.findAll(example);
	    }
	
		
		@GetMapping("/todos") /* ************************************************ Busca todos os clientes */
		@ApiOperation("Busca todos os clientes")
		@ApiResponses({
			@ApiResponse(code = 200, message = "Clientes encontrados com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação")
		})
		    public List<Cliente> findAll( Cliente filtro ){
				return clientes.findAll();
			}

	 
			
}
