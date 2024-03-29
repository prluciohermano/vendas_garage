package io.github.prluciohermano.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.prluciohermano.domain.entity.ItemPedido;
import io.github.prluciohermano.domain.entity.Pedido;
import io.github.prluciohermano.domain.enums.StatusPedido;
import io.github.prluciohermano.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.prluciohermano.rest.dto.InformacoesItemPedidoDTO;
import io.github.prluciohermano.rest.dto.InformacoesPedidoDTO;
import io.github.prluciohermano.rest.dto.PedidoDTO;
import io.github.prluciohermano.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	private PedidoService service;
	
	public PedidoController(PedidoService service) {
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Cria um novo pedido")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Pedido salvo com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public Long save( @RequestBody @Valid PedidoDTO dto ) {
		Pedido pedido = service.salvar(dto);
		return pedido.getId();	
	}
	
	@GetMapping("{id}")
	@ApiOperation("Obter detalhes de um pedido por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Pedido encontrado"),
		@ApiResponse(code = 404, message = "Pedido não encontrado para o ID informado")
	})
	public InformacoesPedidoDTO getById(@PathVariable Long id) {
		return service
				.obterPedidoCompleto(id)
				.map( p -> converter(p) )
				.orElseThrow( () -> 
					new ResponseStatusException(null, "Pedido Não encontrado."));
				
	}
	
	@PatchMapping("{id}")
	 @ApiOperation("Atualiza o Status do pedido")
		@ApiResponses({
			@ApiResponse(code = 200, message = "Status Atualizado"),
			@ApiResponse(code = 404, message = "Status não encontrado ou não atualizado")
		})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Long id,
							 @RequestBody AtualizacaoStatusPedidoDTO dto) {
		String novoStatus = dto.getNovoStatus();
		service.atualizarStatus(id, StatusPedido.valueOf(novoStatus));
	}
	
	private InformacoesPedidoDTO converter(Pedido pedido) {
		return InformacoesPedidoDTO
				.builder()
				.codigo(pedido.getId())
				.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
				.cpf(pedido.getPessoa().getCpf())
				.nomePessoa(pedido.getPessoa().getNome())
				.total(pedido.getTotal())
				.status(pedido.getStatus().name())
				.items(converter(pedido.getItens()))
				.build();		
	}
	
	private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens){
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map(
				item -> InformacoesItemPedidoDTO
				.builder().descricaoProduto(item.getProduto().getDescricao())
				.precoUnitario(item.getProduto().getPreco())
				.quantidade(item.getQuantidade())
				.build()
		).collect(Collectors.toList());
	}
}
