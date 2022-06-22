package io.github.prluciohermano.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedidoDTO {

	private Long produto;
	private Long quantidade;
}
