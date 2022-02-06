package br.com.cotiinformatica.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Pedido;
import br.com.cotiinformatica.repositories.IPedidoRepository;

@Service
@Transactional
public class PedidoService {

	@Autowired
	private IPedidoRepository pedidoRepository;

	/*
	 * Método para gravar os dados do pedido
	 */
	public void create(Pedido pedido) throws Exception {
		pedidoRepository.save(pedido);
	}
}
