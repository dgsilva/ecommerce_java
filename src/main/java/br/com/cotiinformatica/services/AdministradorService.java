package br.com.cotiinformatica.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.repositories.IAdministradorRepository;

@Service
@Transactional
public class AdministradorService {
	
	@Autowired
	private IAdministradorRepository administradorRepository;

}
