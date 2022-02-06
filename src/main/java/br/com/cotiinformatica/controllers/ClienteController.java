package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.adapters.ModelEntityAdapter;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.Endereco;
import br.com.cotiinformatica.mail.MailMessage;
import br.com.cotiinformatica.models.requests.ClientePostRequest;
import br.com.cotiinformatica.models.responses.ClientePostResponse;
import br.com.cotiinformatica.models.validations.ValidationModel;
import br.com.cotiinformatica.services.ClienteService;
import br.com.cotiinformatica.validations.ClientePostValidation;
import br.com.cotiinformatica.validations.EnderecoPostValidation;
import io.swagger.annotations.ApiOperation;

@Controller
public class ClienteController {

	private static final String ENDPOINT = "/api/clientes";

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private MailMessage mailMessage;

	/*
	 * Método de serviço da API para cadastro dos clientes
	 */
	@ApiOperation("Serviço para cadastro de cliente")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<ClientePostResponse> post(@RequestBody ClientePostRequest request) {

		ClientePostResponse response = new ClientePostResponse();

		try {

			//validar os dados do cliente e do endereço
			List<ValidationModel> validationCliente = ClientePostValidation.validate(request);
			List<ValidationModel> validationEndereco = EnderecoPostValidation.validate(request.getEndereco());
			
			//verificar se não há erros de validação
			if(validationCliente.isEmpty() && validationEndereco.isEmpty()) {
				
				//cadastrando o cliente e o endereço..
				Cliente cliente = ModelEntityAdapter.map(request);
				Endereco endereco = ModelEntityAdapter.map(request.getEndereco());
				
				clienteService.create(cliente, endereco);
				
				enviarMensagem(cliente); //envio de email de confirmação
				
				response.setStatusCode(201);
				response.setMessage("Cliente cadastrado com sucesso!");
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			}
			else {
				
				response.setStatusCode(400);
				response.setMessage("Ocorreram erros de validação no envio dos dados.");

				List<ValidationModel> validations = new ArrayList<ValidationModel>();
				for(ValidationModel val : validationCliente)
					validations.add(val);
				for(ValidationModel val : validationEndereco)
					validations.add(val);
				
				response.setValidationErrors(validations);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		} 
		catch(IllegalArgumentException e) {
			
			response.setStatusCode(422);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
		}
		catch (Exception e) {
			
			response.setStatusCode(500);
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	/*
	 * Método para fazer o envio do email para o cliente
	 */
	private void enviarMensagem(Cliente cliente) throws Exception{
		
		String assunto = "Conta de Cliente cadastrada com sucesso - COTI Informática";
		String conteudo = "Olá " + cliente.getNome()
						+ "\n\n"
						+ "Seja bem vindo, sua conta de cliente foi criada com sucesso!"
						+ "\n\n"
						+ "Att,"
						+ "Equipe COTI Informática";
		
		mailMessage.sendMessage(cliente.getEmail(), assunto, conteudo);		
	}	
}


