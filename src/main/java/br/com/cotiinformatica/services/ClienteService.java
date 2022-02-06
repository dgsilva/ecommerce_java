package br.com.cotiinformatica.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.Endereco;
import br.com.cotiinformatica.repositories.IClienteRepository;
import br.com.cotiinformatica.repositories.IEnderecoRepository;
import br.com.cotiinformatica.security.MD5Cryptography;
import br.com.cotiinformatica.security.TokenHelper;

@Service
@Transactional
public class ClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	@Autowired
	private IEnderecoRepository enderecoRepository;

	/*
	 * Método para realizar o cadastro de um cliente
	 */
	public void create(Cliente cliente, Endereco endereco) throws Exception {

		// REGRA: Não permitir o cadastro de um cliente com email duplicado
		if (clienteRepository.findByEmail(cliente.getEmail()) != null) {
			throw new IllegalArgumentException("O email informado já está cadastrado, tente outro.");
		}

		// REGRA: Não permitir o cadastro de um cliente com cpf duplicado
		if (clienteRepository.findByCpf(cliente.getCpf()) != null) {
			throw new IllegalArgumentException("O cpf informado já está cadastrado, tente outro.");
		}

		// REGRA: Não permitir o cadastro de um cliente com telefone duplicado
		if (clienteRepository.findByTelefone(cliente.getTelefone()) != null) {
			throw new IllegalArgumentException("O telefone informado já está cadastrado, tente outro.");
		}

		// criptografar a senha do cliente
		cliente.setSenha(MD5Cryptography.encrypt(cliente.getSenha()));

		// realizar o cadastro do cliente
		clienteRepository.save(cliente);

		// associar o endereço ao cliente
		endereco.setCliente(cliente);

		// realizar o cadastro do endereco
		enderecoRepository.save(endereco);
	}

	/*
	 * Método para realizar a autenticação do cliente
	 */
	public String authenticate(String email, String senha) throws Exception {

		// procurar o cliente atraves do email e senha informados
		Cliente cliente = clienteRepository.findByEmailAndSenha(email, MD5Cryptography.encrypt(senha));

		if (cliente != null) {

			// gerar o TOKEN de autorização
			return TokenHelper.generateToken(cliente.getEmail());

		} else {
			throw new IllegalArgumentException("Acesso Negado, dados inválidos.");
		}
	}

	/*
	 * Método para retornar os dados de um cliente baseado no email
	 */
	public Cliente findByEmail(String email) throws Exception {
		return clienteRepository.findByEmail(email);
	}
}
