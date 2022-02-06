package br.com.cotiinformatica.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Cliente extends Usuario {

	@Column(length = 150, nullable = false)
	private String nome;

	@Column(length = 15, nullable = false, unique = true)
	private String cpf;

	@Column(length = 15, nullable = false, unique = true)
	private String telefone;

	@OneToOne(mappedBy = "cliente")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
}

