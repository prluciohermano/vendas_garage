package io.github.prluciohermano;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.prluciohermano.domain.entity.Cliente;
import io.github.prluciohermano.domain.entity.Produto;
import io.github.prluciohermano.domain.repository.Clientes;


@SpringBootApplication
public class VendasApplication {
	/*
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			Cliente cliente = new Cliente();
			Cliente cliente2 = new Cliente();
			Cliente cliente3 = new Cliente();
			Cliente cliente4 = new Cliente();
			cliente.setNome("Lúcio Hermano");
			cliente.setCpf("47861932168");
			cliente2.setNome("Raquel Vitória");
			cliente3.setNome("Tafenis Batista");
			cliente3.setCpf("50730061191");
			cliente4.setNome("Danrley Menezes Batista");
			clientes.save(cliente);
			clientes.save(cliente2);
			clientes.save(cliente3);
			clientes.save(cliente4);
			
		};
	}
*/
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
