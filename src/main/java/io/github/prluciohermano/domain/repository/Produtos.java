package io.github.prluciohermano.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.prluciohermano.domain.entity.Produto;

@Repository
public interface Produtos extends JpaRepository<Produto, Long> {

}
