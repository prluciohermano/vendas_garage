package io.github.prluciohermano.exception;

@SuppressWarnings("serial")
public class PedidoNaoEncontradoException extends RuntimeException {

	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado.");
	}
}
