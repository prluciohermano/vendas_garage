<<<<<<< HEAD
package io.github.prluciohermano.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException() {
        super("Senha inválida");
    }

}
=======
package io.github.prluciohermano.exception;

@SuppressWarnings("serial")
public class SenhaInvalidaException extends RuntimeException {
	public SenhaInvalidaException() {
		super("Senha inválida.");
	}

}
>>>>>>> 58da461f51bdd56a6f1915e43a0a551db87e3036
