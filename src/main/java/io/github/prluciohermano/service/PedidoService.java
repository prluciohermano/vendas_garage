package io.github.prluciohermano.service;

import java.util.Optional;

import io.github.prluciohermano.domain.entity.Pedido;
import io.github.prluciohermano.domain.enums.StatusPedido;
import io.github.prluciohermano.rest.dto.PedidoDTO;

public interface PedidoService {
	Pedido salvar( PedidoDTO dto );
	
	Optional<Pedido> obterPedidoCompleto(Long id);
	
	void atualizarStatus(Long id, StatusPedido statusPedido);
}
