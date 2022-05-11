package io.github.prluciohermano.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.github.prluciohermano.domain.entity.Cliente;
import io.github.prluciohermano.domain.entity.ItemPedido;
import io.github.prluciohermano.domain.entity.Pedido;
import io.github.prluciohermano.domain.entity.Produto;
import io.github.prluciohermano.domain.repository.Clientes;
import io.github.prluciohermano.domain.repository.ItemsPedido;
import io.github.prluciohermano.domain.repository.Pedidos;
import io.github.prluciohermano.domain.repository.Produtos;
import io.github.prluciohermano.exception.RegraNegocioException;
import io.github.prluciohermano.rest.ItemPedidoDTO;
import io.github.prluciohermano.rest.PedidoDTO;
import io.github.prluciohermano.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

	private final Pedidos repository;
	private final Clientes clientesRepository;
	private final Produtos produtosRepository;
	private final ItemsPedido itemsPedidoRepository;
	
	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clientesRepository.findById(idCliente)
		.orElseThrow( () -> new RegraNegocioException("Código de cliente inválido."));
		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		
		List<ItemPedido> itemsPedido =  converterItems(pedido, dto.getItems());
		repository.save(pedido);
		itemsPedidoRepository.saveAll(itemsPedido);
		pedido.setItens(itemsPedido);
		return pedido;
	}
	
	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if ( items.isEmpty() ) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem ítens");
		}
		
		return items
				.stream()
				.map( dto -> {
					Integer idProduto = dto.getProduto();
					Produto produto = produtosRepository
							.findById(idProduto)
							.orElseThrow(
									() -> new RegraNegocioException(
											"Código de produto inválido: " + idProduto
									));
					ItemPedido itemPedido = new ItemPedido();
					itemPedido.setQuantidade(dto.getQuantidade());
					itemPedido.setPedido(pedido);
					itemPedido.setProduto(produto);
					return itemPedido;
				}).collect(Collectors.toList());
	}
}
