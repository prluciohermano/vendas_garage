package io.github.prluciohermano.rest.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.prluciohermano.domain.entity.Produto;
import io.github.prluciohermano.domain.repository.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	private Produtos repository;
	
	public ProdutoController(Produtos repository) {
		this.repository = repository;
	}

	public Produto save( @RequestBody Produto produto) {
		return repository.save(produto);
	}
}
