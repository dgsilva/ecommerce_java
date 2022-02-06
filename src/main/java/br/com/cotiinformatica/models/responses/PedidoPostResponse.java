package br.com.cotiinformatica.models.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoPostResponse {

	private Integer statusCode;
	private String message;
}
