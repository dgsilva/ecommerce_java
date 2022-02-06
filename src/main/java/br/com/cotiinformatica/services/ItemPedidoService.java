package br.com.cotiinformatica.services;

import javax.transaction.Transactional;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.ItemPedido;
import br.com.cotiinformatica.repositories.IItemPedidoRepository;

@Service
@Transactional
public class ItemPedidoService {

	@Autowired
	private IItemPedidoRepository itemPedidoRepository;

	/*
	 * Método para realizar o cadastro de itens de pedido
	 */
	public void create(List<ItemPedido> itensPedido) throws Exception {
		itemPedidoRepository.saveAll(itensPedido);
	}
}
