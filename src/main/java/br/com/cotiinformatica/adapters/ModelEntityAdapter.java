package br.com.cotiinformatica.adapters;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.Endereco;
import br.com.cotiinformatica.models.requests.ClientePostRequest;
import br.com.cotiinformatica.models.requests.EnderecoPostRequest;

public class ModelEntityAdapter {

	/*
	 * Métodos para receber como argumento um objeto REQUEST e transferir os seus
	 * dados para uma entidade
	 */

	public static Cliente map(ClientePostRequest request) {

		Cliente cliente = new Cliente();

		cliente.setNome(request.getNome());
		cliente.setTelefone(request.getTelefone());
		cliente.setCpf(request.getCpf());
		cliente.setEmail(request.getEmail());
		cliente.setSenha(request.getSenha());

		return cliente;
	}

	public static Endereco map(EnderecoPostRequest request) {

		Endereco endereco = new Endereco();

		endereco.setLogradouro(request.getLogradouro());
		endereco.setNumero(request.getNumero());
		endereco.setComplemento(request.getComplemento());
		endereco.setBairro(request.getBairro());
		endereco.setCidade(request.getCidade());
		endereco.setEstado(request.getEstado());
		endereco.setCep(request.getCep());

		return endereco;
	}
}

