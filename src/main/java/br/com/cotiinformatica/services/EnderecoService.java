package br.com.cotiinformatica.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.repositories.IEnderecoRepository;

@Service
@Transactional
public class EnderecoService {

	@Autowired
	private IEnderecoRepository enderecoRepository;
}
