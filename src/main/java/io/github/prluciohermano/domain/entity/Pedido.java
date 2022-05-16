package io.github.prluciohermano.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.github.prluciohermano.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne()
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(name = "data_pedido")
	private LocalDate dataPedido;
	
	@Column(precision = 20, scale = 2)
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusPedido status;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;
	
	public List<ItemPedido> getItens() {
		if(this.itens == null) {
			this.itens = new ArrayList<>();
		}
		return itens;
	}
}
/* Quando for acrescentar algum atributo precisa seguir os passos do video 55
 *  da aula do Curso de spring-boot-especialista da Udemy*/
