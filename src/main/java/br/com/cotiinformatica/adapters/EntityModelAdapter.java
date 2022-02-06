package br.com.cotiinformatica.adapters;


import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.models.responses.ProdutoGetResponse;

public class EntityModelAdapter {

	/*
	 * Métodos para receber como argumento um objeto Entity e transferir os seus
	 * dados para um objeto do tipo RESPONSE
	 */

	public static List<ProdutoGetResponse> map(List<Produto> produtos) {

		List<ProdutoGetResponse> result = new ArrayList<ProdutoGetResponse>();

		for (Produto produto : produtos) {

			ProdutoGetResponse response = new ProdutoGetResponse();
			response.setId(produto.getId());
			response.setNome(produto.getNome());
			response.setDescricao(produto.getDescricao());
			response.setPreco(produto.getPreco());
			response.setFoto(produto.getFoto());
			response.setStatus(produto.getStatus().toString());

			result.add(response);
		}

		return result;
	}
}

