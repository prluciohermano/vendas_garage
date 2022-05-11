package io.github.prluciohermano.service;

import io.github.prluciohermano.domain.entity.Pedido;
import io.github.prluciohermano.rest.PedidoDTO;

public interface PedidoService {
	Pedido salvar( PedidoDTO dto );
}
