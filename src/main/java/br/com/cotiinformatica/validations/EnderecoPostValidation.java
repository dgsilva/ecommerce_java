package br.com.cotiinformatica.validations;

import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.models.requests.EnderecoPostRequest;
import br.com.cotiinformatica.models.validations.ValidationModel;

public class EnderecoPostValidation {

	/*
	 * Método para validar os dados da requisição de cadastro de endereço
	 */
	public static List<ValidationModel> validate(EnderecoPostRequest request) {

		List<ValidationModel> result = new ArrayList<ValidationModel>();

		if (request.getLogradouro() == null || request.getLogradouro().trim().length() == 0) {
			result.add(new ValidationModel("logradouro", "Informe o logradouro do endereço."));
		}

		if (request.getNumero() == null || request.getNumero().trim().length() == 0) {
			result.add(new ValidationModel("numero", "Informe o número do endereço."));
		}

		if (request.getComplemento() == null || request.getComplemento().trim().length() == 0) {
			result.add(new ValidationModel("complemento", "Informe o complemento do endereço."));
		}

		if (request.getBairro() == null || request.getBairro().trim().length() == 0) {
			result.add(new ValidationModel("bairro", "Informe o bairro do endereço."));
		}

		if (request.getCidade() == null || request.getCidade().trim().length() == 0) {
			result.add(new ValidationModel("cidade", "Informe a cidade do endereço."));
		}

		if (request.getEstado() == null || request.getEstado().trim().length() == 0) {
			result.add(new ValidationModel("estado", "Informe o estado do endereço."));
		}

		if (request.getCep() == null || request.getCep().trim().length() == 0) {
			result.add(new ValidationModel("cep", "Informe o cep do endereço."));
		}

		return result;
	}

}

