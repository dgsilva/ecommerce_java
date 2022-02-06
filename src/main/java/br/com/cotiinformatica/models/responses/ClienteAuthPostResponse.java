package br.com.cotiinformatica.models.responses;

import java.util.List;

import br.com.cotiinformatica.models.validations.ValidationModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteAuthPostResponse {

	private Integer statusCode;
	private String message;
	private String accessToken;
	private List<ValidationModel> validationErrors;

}

