package br.com.cotiinformatica.models.requests;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoPostRequest {

	private Date dataHoraPedido;
	private Double valorPedido;	
	private List<ItemPedidoPostRequest> itensPedido;	
}

