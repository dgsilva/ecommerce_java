package br.com.cotiinformatica.models.requests;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientePostRequest {

	private String nome;
	private String telefone;
	private String cpf;
	private String email;
	private String senha;
	private String senhaConfirmacao;
	private EnderecoPostRequest endereco;
}
