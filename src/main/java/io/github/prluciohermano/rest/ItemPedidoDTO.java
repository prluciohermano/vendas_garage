package io.github.prluciohermano.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedidoDTO {

	private Integer produto;
	private Integer quantidade;
}
