package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.services.AdministradorService;

@RestController

public class AdministradorController {
	@Autowired
	private AdministradorService administradorService;

}
