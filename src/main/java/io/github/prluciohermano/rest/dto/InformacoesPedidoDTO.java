package io.github.prluciohermano.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {
	private Long codigo;
	private String cpf;
	private String nomePessoa;
	private BigDecimal total;
	private String dataPedido;
	private String status;
	private List<InformacoesItemPedidoDTO> items;

}
