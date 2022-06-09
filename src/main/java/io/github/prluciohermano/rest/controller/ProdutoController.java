package io.github.prluciohermano.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
import io.github.prluciohermano.domain.entity.Produto;
import io.github.prluciohermano.domain.repository.Produtos;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	private Produtos repository;

	public ProdutoController(Produtos repository) {
		this.repository = repository;
	}

	@PostMapping /* *****************************************************  Salvar Produto */
	@ResponseStatus(CREATED)
	@ApiOperation("Cria um novo produto")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Produto salvo com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public Produto save( @RequestBody @Valid Produto produto) {
		return repository.save(produto);
	}

	@PutMapping("{id}") /* ************************************************ Atualizar um Produto */
	@ResponseStatus(NO_CONTENT)
	@ApiOperation("Altera os dados de um produto")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Produto alterado com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public void update( @PathVariable Integer id, 
						@RequestBody
						@Valid Produto produto ) {
		repository
			.findById(id)
			.map( p -> {
			produto.setId(p.getId());
			repository.save(produto);
			return produto;
		}).orElseThrow( () -> new ResponseStatusException(NOT_FOUND,
				"Cliente não encontrado") );
	}
	
	@DeleteMapping("{id}")  /* ********************************************* Deletar um Produto */
	@ResponseStatus(NO_CONTENT)
	@ApiOperation("Deleta um produto")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Produto deletado com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public void delete(@PathVariable Integer id) {
		repository
			.findById(id)
			.map( p -> {
			repository.delete(p);
			return Void.TYPE;
		}).orElseThrow( () -> new ResponseStatusException(NOT_FOUND,
				"Cliente não encontrado") );
	}
	
	@GetMapping("{id}") /* ************************************************ Buscar produto por ID */
	@ApiOperation("Obter detalhes de um produto pelo ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Produto encontrado"),
		@ApiResponse(code = 404, message = "Produto não encontrado para o ID informado")
	})
	public Produto getById(@PathVariable Integer id) {
		return repository
				.findById(id)
				.orElseThrow( () -> new ResponseStatusException(NOT_FOUND,
					"Cliente não encontrado") );
	}
	
	@GetMapping
	@ApiOperation("Busca produtos por parâmetros")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Produto encontrado com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public List<Produto> find(Produto filtro){
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(
						ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		return repository.findAll(example);
	}
	
	@GetMapping("/todos") /* ************************************************ Busca todos os produtos */
	@ApiOperation("Busca todos os produtos")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Clientes encontrados com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	    public List<Produto> findAll( Produto filtro ){
			return repository.findAll();
		}
}
