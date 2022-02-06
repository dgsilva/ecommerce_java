package br.com.cotiinformatica.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoGetResponse {

	private Integer id;
	private String nome;
	private String descricao;
	private Double preco;
	private String foto;
	private String status;

}
