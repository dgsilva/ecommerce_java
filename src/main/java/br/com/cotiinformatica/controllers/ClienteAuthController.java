package br.com.cotiinformatica.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.models.requests.ClienteAuthPostRequest;
import br.com.cotiinformatica.models.responses.ClienteAuthPostResponse;
import br.com.cotiinformatica.models.validations.ValidationModel;
import br.com.cotiinformatica.services.ClienteService;
import br.com.cotiinformatica.validations.ClienteAuthPostValidation;
import io.swagger.annotations.ApiOperation;

@RestController
public class ClienteAuthController {

	private static final String ENDPOINT = "/api/clientes-auth";

	@Autowired
	private ClienteService clienteService;

	/*
	 * Método de serviço da API para autenticação dos clientes
	 */
	@ApiOperation("Servico para autenticação do cliente")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<ClienteAuthPostResponse> post(@RequestBody ClienteAuthPostRequest request) {

		ClienteAuthPostResponse response = new ClienteAuthPostResponse();

		try {

			//validar os dados
			List<ValidationModel> validation = ClienteAuthPostValidation.validate(request);
			
			//verificar se não há erros de validação
			if(validation.isEmpty()) {
				
				String accessToken = clienteService.authenticate(request.getEmail(), request.getSenha());
				
				response.setStatusCode(200);
				response.setMessage("Cliente autenticado com sucesso!");
				response.setAccessToken(accessToken);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			else {
				
				response.setStatusCode(400);
				response.setMessage("Ocorreram erros de validação no envio dos dados.");
				response.setValidationErrors(validation);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		} 
		catch(IllegalArgumentException e) {
			
			response.setStatusCode(401);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
		catch (Exception e) {
			
			response.setStatusCode(500);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
