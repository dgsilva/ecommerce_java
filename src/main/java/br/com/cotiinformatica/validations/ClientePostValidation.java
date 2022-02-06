package br.com.cotiinformatica.validations;

import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.models.requests.ClientePostRequest;
import br.com.cotiinformatica.models.validations.ValidationModel;

public class ClientePostValidation {

	/*
	 * Método para validar os dados da requisição de cadastro de cliente
	 */
	public static List<ValidationModel> validate(ClientePostRequest request) {

		List<ValidationModel> result = new ArrayList<ValidationModel>();

		if (request.getNome() == null || request.getNome().trim().length() < 6) {
			result.add(new ValidationModel("nome", "Informe no mínimo 6 caracteres."));
		}

		if (request.getTelefone() == null || request.getTelefone().trim().length() != 11) {
			result.add(new ValidationModel("telefone", "Informe exatamente 11 caracteres (DDD + Telefone)."));
		}

		if (request.getCpf() == null || request.getCpf().trim().length() != 11) {
			result.add(new ValidationModel("cpf", "Informe exatamente 11 caracteres numéricos."));
		}

		if (request.getEmail() == null || request.getEmail().trim().length() == 0) {
			result.add(new ValidationModel("email", "Informe o endereço de email."));
		}

		if (request.getSenha() == null || request.getSenha().trim().length() < 6) {
			result.add(new ValidationModel("senha", "Informe no mínimo 6 caracteres."));
		}

		if (request.getSenhaConfirmacao() == null || !request.getSenhaConfirmacao().equals(request.getSenha())) {
			result.add(new ValidationModel("senhaConfirmacao", "Senhas não conferem."));
		}

		return result;
	}

}

