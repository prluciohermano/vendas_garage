package io.github.prluciohermano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "io.github.prluciohermano.domain.entity")
@ComponentScan(basePackages = { "io.github.prluciohermano.*" })
@EnableJpaRepositories(basePackages = { "io.github.prluciohermano.domain.repository" })
public class GarageApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarageApplication.class, args);
    }
    
}