package io.github.prluciohermano;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import io.github.prluciohermano.domain.entity.Cliente;
import io.github.prluciohermano.domain.repository.Clientes;

@SpringBootApplication
@RestController
public class VendasApplication {
		
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args ->{
			/* Salvando os dados */
			clientes.salvar(new Cliente(1, "Lúcio Hermano"));
			clientes.salvar(new Cliente(2, "Raquel Vitória"));
			
			/* Listando os dados */
			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
		};
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
