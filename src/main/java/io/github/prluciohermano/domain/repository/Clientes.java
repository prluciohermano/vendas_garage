package io.github.prluciohermano.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.prluciohermano.domain.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {
	
	List<Cliente> existsByNome = null;

	
	List<Cliente> findByNomeOrId(String nome, Integer id);
	

	@Query(value = "select c from Cliente c where c.nome like :nome")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
}
