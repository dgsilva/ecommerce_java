package br.com.cotiinformatica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.adapters.EntityModelAdapter;
import br.com.cotiinformatica.models.responses.ProdutoGetResponse;
import br.com.cotiinformatica.services.ProdutoService;
import io.swagger.annotations.ApiOperation;

@RestController
public class ProdutoController {

	private static final String ENDPOINT = "/api/produtos";

	@Autowired
	private ProdutoService produtoService;

	/*
	 * Método de serviço da API para consulta dos produtos
	 */
	@ApiOperation(value = "Retorna uma lista de Produto")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<List<ProdutoGetResponse>> getAll() {

		try {
			List<ProdutoGetResponse> result = EntityModelAdapter.map(produtoService.getAll());
			return ResponseEntity.status(HttpStatus.OK).body(result);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
