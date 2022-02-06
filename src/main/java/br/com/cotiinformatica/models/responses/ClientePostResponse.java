package br.com.cotiinformatica.models.responses;

import java.util.List;

import br.com.cotiinformatica.models.validations.ValidationModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientePostResponse {
	
	private Integer statusCode;
	private String message;
	private List<ValidationModel>validationErrors;

}
