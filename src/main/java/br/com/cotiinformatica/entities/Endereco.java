package br.com.cotiinformatica.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column(length = 250, nullable = false)
	private String logradouro;

	@Column(length = 150, nullable = false)
	private String complemento;

	@Column(length = 15, nullable = false)
	private String numero;

	@Column(length = 100, nullable = false)
	private String bairro;

	@Column(length = 150, nullable = false)
	private String cidade;

	@Column(length = 2, nullable = false)
	private String estado;

	@Column(length = 10, nullable = false)
	private String cep;

	@OneToOne
	@JoinColumn(name = "idcliente", nullable = false, unique = true)
	private Cliente cliente;
}



