package io.github.prluciohermano.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.prluciohermano.domain.entity.ItemPedido;

public interface ItemsPedido extends JpaRepository<ItemPedido, Long> {

}
