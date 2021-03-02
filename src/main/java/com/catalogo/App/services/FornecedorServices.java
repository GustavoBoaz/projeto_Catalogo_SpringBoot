package com.catalogo.App.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.App.models.Fornecedor;
import com.catalogo.App.repositories.FornecedorRepository;

@Service
public class FornecedorServices {

	private @Autowired FornecedorRepository repositoryFornecedor;
	
	// Salva um novo fornecedor
	public Fornecedor novoFornecedor(Fornecedor novoFornecedor) {
		return repositoryFornecedor.save(novoFornecedor);
	}
	
	// Deletar um fornecedor
	public void deletarFornecedor(Long idFornecedor) throws IllegalArgumentException {
		repositoryFornecedor.deleteById(idFornecedor);
	}
	
	// Procurar pelo Nome
	public List<Fornecedor> produrarPorNome(String nomeFornecedor) {
		return repositoryFornecedor.findByNomeContaining(nomeFornecedor);
	}
	
}
