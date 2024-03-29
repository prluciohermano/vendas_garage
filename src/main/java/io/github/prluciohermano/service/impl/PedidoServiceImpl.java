package io.github.prluciohermano.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.github.prluciohermano.domain.entity.ItemPedido;
import io.github.prluciohermano.domain.entity.Pedido;
import io.github.prluciohermano.domain.entity.Pessoa;
import io.github.prluciohermano.domain.entity.Produto;
import io.github.prluciohermano.domain.enums.StatusPedido;
import io.github.prluciohermano.domain.repository.ItemsPedido;
import io.github.prluciohermano.domain.repository.Pedidos;
import io.github.prluciohermano.domain.repository.Pessoas;
import io.github.prluciohermano.domain.repository.Produtos;
import io.github.prluciohermano.exception.PedidoNaoEncontradoException;
import io.github.prluciohermano.exception.RegraNegocioException;
import io.github.prluciohermano.rest.dto.ItemPedidoDTO;
import io.github.prluciohermano.rest.dto.PedidoDTO;
import io.github.prluciohermano.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

	private final Pedidos repository;
	private final Pessoas pessoasRepository;
	private final Produtos produtosRepository;
	private final ItemsPedido itemsPedidoRepository;
	
	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Long idPessoa = dto.getPessoa();
		Pessoa pessoa = pessoasRepository.findById(idPessoa)
		.orElseThrow( () -> new RegraNegocioException("Código de pessoa inválido."));
		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setPessoa(pessoa);
		pedido.setStatus(StatusPedido.REALIZADO);
		
		List<ItemPedido> itemsPedido =  converterItems(pedido, dto.getItems());
		repository.save(pedido);
		itemsPedidoRepository.saveAll(itemsPedido);
		pedido.setItens(itemsPedido);
		return pedido;
	}
	
	public Optional<Pedido> obterPedidoCompleto( Long id ) {
		return repository.findByIdFetchItens(id);
	}

	public void atualizarStatus( Long id, StatusPedido statusPedido ) {
		repository
				.findById(id)
				.map( pedido -> {
					pedido.setStatus(statusPedido);
					return repository.save(pedido);
				}).orElseThrow( () -> new PedidoNaoEncontradoException());
		
	}
	
	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if ( items.isEmpty() ) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem ítens");
		}
		
		return items
				.stream()
				.map( dto -> {
					Long idProduto = dto.getProduto();
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
