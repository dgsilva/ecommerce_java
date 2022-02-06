package br.com.cotiinformatica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cotiinformatica.entities.Endereco;


public interface IEnderecoRepository extends JpaRepository<Endereco, Integer> {

}
