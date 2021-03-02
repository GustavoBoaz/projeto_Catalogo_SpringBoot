package com.catalogo.App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.App.models.Fornecedor;
import com.catalogo.App.models.Produto;
import com.catalogo.App.repositories.FornecedorRepository;
import com.catalogo.App.repositories.ProdutoRepository;

@Service
public class ProdutoServices {
	
	private @Autowired ProdutoRepository repositoryProduto;
	private @Autowired FornecedorRepository repositoryFornecedor;

	// Salva um novo produto
	public Produto novoProduto(Produto novoProduto) {
		return repositoryProduto.save(novoProduto);
	}
	
	// Deletar um produto
	public void deletarProduto(Long idProduto, Long idFornecedor) throws IllegalArgumentException {
		repositoryFornecedor.findById(idFornecedor).ifPresent(
				fornecedor -> {
					repositoryProduto.findById(idProduto).ifPresent(
							produto -> {
								produto.getFornecedores().remove(fornecedor);
								repositoryProduto.save(produto);
							});
				});
	}
	
	// Novo Item Catalogado
	public Fornecedor novoItemNoCatalogo(Long idProduto, Long idFornecedor) {
		Optional<Fornecedor> fornecedor = repositoryFornecedor.findById(idFornecedor);
		Optional<Produto> produto = repositoryProduto.findById(idProduto);
		
		if(fornecedor.isPresent() && produto.isPresent()) {
			produto.get().getFornecedores().add(fornecedor.get());
			repositoryProduto.save(produto.get());
		}
		
		return repositoryFornecedor.findById(idFornecedor).get();
	}
}
