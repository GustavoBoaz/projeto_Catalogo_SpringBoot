package com.catalogo.App.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Fornecedor {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private @NotNull @Size(min = 2, max = 45, message = "Error size") String nome;
	private @NotNull @Size(min = 2, max = 45, message = "Error size") String email;
	private @NotNull @Size(min = 2, max = 45, message = "Error size") String telefone;
	private @NotNull @Size(min = 2, max = 45, message = "Error size") String endereco;
	
	@ManyToMany(mappedBy = "fornecedores" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("fornecedores")
	private List<Produto> produtos = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
