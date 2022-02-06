package br.com.cotiinformatica.services;

import java.util.List;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.repositories.IProdutoRepository;

@Service
@Transactional
public class ProdutoService {

	@Autowired
	private IProdutoRepository produtoRepository;

	public List<Produto> getAll() throws Exception {
		return (List<Produto>) produtoRepository.findAll();
	}

	public Produto getById(Integer idProduto) throws Exception {

		// captura os dados do produto através do id
		Optional<Produto> produto = produtoRepository.findById(idProduto);

		// verificar se o produto foi encontrado
		if (produto != null) {
			return produto.get(); // retornando os dados do produto
		} else {
			return null; //retornando vazio
		}
	}
}
