package br.com.cotiinformatica.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.cotiinformatica.entities.Pedido;

public interface IPedidoRepository extends CrudRepository<Pedido, Integer> {

}
