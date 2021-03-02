package com.catalogo.App.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.App.models.Fornecedor;
import com.catalogo.App.services.FornecedorServices;

@RestController
@RequestMapping("/v1/fornecedor")
@CrossOrigin("*")
public class FornecedorController {
	
	private @Autowired FornecedorServices services;
	
	@PostMapping("/novo")
	public ResponseEntity<Fornecedor> novo(@Valid @RequestBody Fornecedor novoFornecedor){
		return new ResponseEntity<Fornecedor>(
				services.novoFornecedor(novoFornecedor),
				HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Fornecedor>> pegar(@RequestParam(defaultValue = "") String nomeFornecedor){
		return new ResponseEntity<List<Fornecedor>>(
				services.produrarPorNome(nomeFornecedor),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id_Fornecedor}")
	public ResponseEntity<?> delete(@PathVariable(value = "id_Fornecedor") Long idFornecedor){
		try {
			services.deletarFornecedor(idFornecedor);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Fornecedor inexistente para Deletar. ERRO: " + e, HttpStatus.FOUND);
		}
	}
}
