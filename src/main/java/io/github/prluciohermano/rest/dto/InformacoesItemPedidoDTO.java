package io.github.prluciohermano.rest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesItemPedidoDTO {

	private String descricaoProduto;
	private BigDecimal precoUnitario;
	private Long quantidade;
}
