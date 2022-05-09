package io.github.prluciohermano;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.prluciohermano.domain.entity.Cliente;
import io.github.prluciohermano.domain.repository.Clientes;


@SpringBootApplication
public class VendasApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			Cliente cliente = new Cliente();
			Cliente cliente2 = new Cliente();
			cliente.setNome("Lúcio Hermano");
			clientes.save(cliente);
			cliente2.setNome("Raquel Vitória");
			clientes.save(cliente2);
		};
	}

	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
