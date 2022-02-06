package br.com.cotiinformatica.entities;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cotiinformatica.enums.StatusPedido;
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
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "idcliente", nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itensPedido;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataHoraPedido;

	@Column(nullable = false)
	private Double valorPedido;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private StatusPedido pedido;

}


