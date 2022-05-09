package io.github.prluciohermano.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "nome", length = 100)
	private String nome;
	
	@OneToMany(mappedBy = "cliente")
	private Set<Pedido> pedidos;
	
	
	public Cliente() {}
	
	public Cliente(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Set<Pedido> getPedidos() {
		return pedidos;
	}
	
	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Cliente {id=" + 
				id + 
				", nome= '" + nome + '\'' +
				'}';
	}
	
}
