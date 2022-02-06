package br.com.cotiinformatica.validations;
	
	import java.util.ArrayList;
	import java.util.List;

	import br.com.cotiinformatica.models.requests.ClienteAuthPostRequest;
	import br.com.cotiinformatica.models.validations.ValidationModel;

	public class ClienteAuthPostValidation {

		/*
		 * Método para validar os dados da requisição de autenticação de cliente
		 */
		public static List<ValidationModel> validate(ClienteAuthPostRequest request) {

			List<ValidationModel> result = new ArrayList<ValidationModel>();

			if (request.getEmail() == null || request.getEmail().trim().length() == 0) {
				result.add(new ValidationModel("email", "Informe o endereço de email."));
			}

			if (request.getSenha() == null || request.getSenha().trim().length() < 6) {
				result.add(new ValidationModel("senha", "Informe no mínimo 6 caracteres."));
			}

			return result;
		}

	}



