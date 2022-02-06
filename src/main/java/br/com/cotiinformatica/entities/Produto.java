package br.com.cotiinformatica.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.cotiinformatica.enums.StatusProduto;
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
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column(length = 150, nullable = false)
	private String nome;

	@Column(length = 500, nullable = false)
	private String descricao;

	@Column(nullable = false)
	private Double preco;

	@Column(length = 250, nullable = false)
	private String foto;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private StatusProduto status;

	@OneToMany(mappedBy = "produto")
	private List<ItemPedido> itensPedido;
}


