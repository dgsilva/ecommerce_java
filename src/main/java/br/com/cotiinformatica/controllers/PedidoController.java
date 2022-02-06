package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.ItemPedido;
import br.com.cotiinformatica.entities.Pedido;
import br.com.cotiinformatica.enums.StatusPedido;
import br.com.cotiinformatica.models.requests.ItemPedidoPostRequest;
import br.com.cotiinformatica.models.requests.PedidoPostRequest;
import br.com.cotiinformatica.models.responses.PedidoPostResponse;
import br.com.cotiinformatica.security.TokenHelper;
import br.com.cotiinformatica.services.ClienteService;
import br.com.cotiinformatica.services.ItemPedidoService;
import br.com.cotiinformatica.services.PedidoService;
import br.com.cotiinformatica.services.ProdutoService;
import io.swagger.annotations.ApiOperation;

@RestController
public class PedidoController {

	private static final String ENDPOINT = "/api/pedidos";

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ItemPedidoService itemPedidoService;

	@ApiOperation("Serviço para realização de pedido")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<PedidoPostResponse> post(@RequestBody PedidoPostRequest request, HttpServletRequest httpServletRequest) {

		PedidoPostResponse response = new PedidoPostResponse();

		try {
			
			//obter os dados do cliente autenticado
			Cliente cliente = clienteService.findByEmail(getUserCredential(httpServletRequest));
			
			//montando o pedido
			Pedido pedido = new Pedido();
			pedido.setCliente(cliente); //cliente do pedido
			pedido.setDataHoraPedido(request.getDataHoraPedido());
			pedido.setValorPedido(request.getValorPedido());
			pedido.setPedido(StatusPedido.EM_ANALISE);
			
			//montar os itens de pedido
			List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
			for(ItemPedidoPostRequest item : request.getItensPedido()) {
				//obter os dados do produto
				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setProduto(produtoService.getById(item.getIdProduto()));
				itemPedido.setPrecoUnitario(item.getPrecoUnitario());
				itemPedido.setQuantidadeItens(item.getQuantidadeItens());
				itemPedido.setPedido(pedido);
				
				itensPedido.add(itemPedido);
			}
			
			pedidoService.create(pedido); //gravando o pedido
			itemPedidoService.create(itensPedido); //gravando os itens do pedido
				
			response.setStatusCode(201);
			response.setMessage("Pedido realizado com sucesso!");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} 
		catch (Exception e) {
			
			response.setStatusCode(500);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	// método para extrair o email do usuário contido no token
	private String getUserCredential(HttpServletRequest httpServletRequest) {

		// capturar o token de autenticação
		String token = httpServletRequest.getHeader("Authorization").replace("Bearer", "").trim();

		// obter o email do cliente deste token
		return TokenHelper.getEmailFromToken(token);
	}
}
