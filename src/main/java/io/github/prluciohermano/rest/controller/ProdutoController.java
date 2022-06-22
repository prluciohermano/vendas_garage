package io.github.prluciohermano.rest.controller;

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

import io.github.prluciohermano.domain.entity.Produto;
import io.github.prluciohermano.domain.repository.Produtos;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private Produtos produtos;


	@PostMapping /* *****************************************************  Salvar Produto */
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Cria um novo produto")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Produto salvo com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public Produto save( @RequestBody @Valid Produto produto) {
		return produtos.save(produto);
	}

	@PutMapping("{id}") /* ************************************************ Atualizar um Produto */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Altera os dados de um produto")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Produto alterado com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public void update( @PathVariable Long id, 
						@RequestBody
						@Valid Produto produto ) {
		produtos
			.findById(id)
			.map( p -> {
			produto.setId(p.getId());
			produtos.save(produto);
			return produto;
		}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Pessoa não encontrado") );
	}
	
	@DeleteMapping("{id}")  /* ********************************************* Deletar um Produto */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Deleta um produto")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Produto deletado com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public void delete(@PathVariable Long id) {
		produtos
			.findById(id)
			.map( p -> {
			produtos.delete(p);
			return Void.TYPE;
		}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Pessoa não encontrada") );
	}
	
	@GetMapping("/{id}") /* ************************************************ Buscar produto por ID */
	@ApiOperation("Obter detalhes de um produto pelo ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Produto encontrado"),
		@ApiResponse(code = 404, message = "Produto não encontrado para o ID informado")
	})
	public Produto getProdutoById(
			@PathVariable @ApiParam("id") Long id) {
		return produtos.findById(id)
				.orElseThrow( () ->
				new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Pessoa não encontrada"));
	}
	
//	@GetMapping
//	@ApiOperation("Busca produtos por parâmetros")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Produto encontrado com sucesso"),
//		@ApiResponse(code = 400, message = "Erro de validação")
//	})
//	public List<Produto> find(Produto filtro){
//		ExampleMatcher matcher = ExampleMatcher
//				.matching()
//				.withIgnoreCase()
//				.withStringMatcher(
//						ExampleMatcher.StringMatcher.CONTAINING);
//		Example example = Example.of(filtro, matcher);
//		return produtos.findAll(example);
//	}
	
	@GetMapping("/") /* ************************************************ Busca todos os produtos */
	@ApiOperation("Busca todos os produtos")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Pessoas encontrados com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	    public List<Produto> findAll(){
			return produtos.findAll();
		}
}
