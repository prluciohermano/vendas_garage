package io.github.prluciohermano.domain.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome", length = 100)
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@Column(name = "cpf", length = 11)
	@NotEmpty(message = "{campo.cpf.obrigatorio}")
	//@CPF(message = "{campo.cpf.invalido}")
	private String cpf;
	
	@Column(name = "rg", length = 100)
	@NotEmpty(message = "{campo.rg.obrigatorio}")
	private String rg;
	
	@Column(name = "sexo", length = 100)
	@NotEmpty(message = "{campo.sexo.obrigatorio}")
	private String sexo;
	
	@Column(name = "cep", length = 100)
	@NotEmpty(message = "{campo.cep.obrigatorio}")
	private String cep;
	
	@Column(name = "rua", length = 100)
	@NotEmpty(message = "{campo.rua.obrigatorio}")
	private String rua;
	
	@Column(name = "numero", length = 100)
	@NotEmpty(message = "{campo.numero.obrigatorio}")
	private String numero;
	
	@Column(name = "bairro", length = 100)
	@NotEmpty(message = "{campo.bairro.obrigatorio}")
	private String bairro;
	
	@Column(name = "comp", length = 100)
	private String comp;
	
	@Column(name = "cidade", length = 100)
	@NotEmpty(message = "{campo.cidade.obrigatorio}")
	private String cidade;
	
	@Column(name = "uf", length = 100)
	@NotEmpty(message = "{campo.uf.obrigatorio}")
	private String uf;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY )
	private Set<Pedido> pedidos;
	
}
/* Quando for acrescentar algum atributo precisa seguir os passos do video 55
 *  da aula do Curso de spring-boot-especialista da Udemy*/
 