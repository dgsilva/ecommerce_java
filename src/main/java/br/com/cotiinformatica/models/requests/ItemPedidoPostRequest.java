package br.com.cotiinformatica.models.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemPedidoPostRequest {

	private Integer idProduto;
	private Integer quantidadeItens;
	private Double precoUnitario;

}


