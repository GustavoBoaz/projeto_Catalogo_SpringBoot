package com.catalogo.App.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.App.models.Fornecedor;
import com.catalogo.App.models.Produto;
import com.catalogo.App.services.ProdutoServices;

@RestController
@RequestMapping("/v1/produto")
@CrossOrigin("*")
public class ProdutoController {

	private @Autowired ProdutoServices services;
	
	@PostMapping("/{id_Fornecedor}/novo")
	public ResponseEntity<Fornecedor> novo(
			@Valid @RequestBody Produto novoProduto,
			@PathVariable(value = "id_Fornecedor") Long idFornecedor){
		
		Long idProduto = services.novoProduto(novoProduto).getId();
		return new ResponseEntity<Fornecedor>(
				services.novoItemNoCatalogo(idProduto, idFornecedor),
				HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id_Produto}/{id_Fornecedor}")
	public ResponseEntity<?> delete(
			@PathVariable(value = "id_Produto") Long idProduto,
			@PathVariable(value = "id_Fornecedor") Long idFornecedor){
		
		try {
			services.deletarProduto(idProduto, idFornecedor);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<String>(
					"Produto inexistente para Deletar. ERRO: " + e,
					HttpStatus.FOUND);
		}
	}
}
