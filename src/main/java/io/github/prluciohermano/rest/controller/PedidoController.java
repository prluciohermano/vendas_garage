package io.github.prluciohermano.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.prluciohermano.domain.entity.Pedido;
import io.github.prluciohermano.rest.PedidoDTO;
import io.github.prluciohermano.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	private PedidoService service;
	
	public PedidoController(PedidoService service) {
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save( @RequestBody PedidoDTO dto ) {
		Pedido pedido = service.salvar(dto);
		return pedido.getId();
		
	}
}
