package io.github.prluciohermano;

import java.util.List;

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
		return args ->{
			/* Salvando os dados */
			System.out.println("\nSalvando o cliente");
			clientes.save(new Cliente(1, "Lúcio Hermano"));
			clientes.save(new Cliente(2, "Raquel Vitória"));
			
			/* Listando os dados */
			System.out.println("\nListando o cliente");
			List<Cliente> result = clientes.encontrarPorNome("Lúcio Hermano");
			result.forEach(System.out::println);
			
		};		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
