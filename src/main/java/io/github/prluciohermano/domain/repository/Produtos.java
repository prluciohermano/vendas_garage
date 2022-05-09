package io.github.prluciohermano.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.prluciohermano.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer> {

}
