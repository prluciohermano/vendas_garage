package io.github.prluciohermano.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.prluciohermano.domain.entity.Pedido;
import io.github.prluciohermano.domain.entity.Pessoa;

public interface Pedidos extends JpaRepository<Pedido, Long> {

	List<Pedido> findByPessoa(Pessoa pessoa);
	
	@Query(" select p from Pedido p left join fetch p.itens where p.id = :id ")
	Optional<Pedido> findByIdFetchItens(@Param("id") Long id);
}
/* Quando for acrescentar algum atributo precisa seguir os passos do video 55
 *  da aula do Curso de spring-boot-especialista da Udemy*/