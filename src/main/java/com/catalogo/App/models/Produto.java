package com.catalogo.App.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.catalogo.App.models.util.ProdutoAceitos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Produto {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private @Enumerated(EnumType.STRING) ProdutoAceitos produto;
	private @NotNull @Size(min = 2, max = 45, message = "Error size") String preco;
	private @NotNull @Size(min = 2, max = 45, message = "Error size") String caracteristica;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "fornecedor_produto",
			joinColumns = @JoinColumn(name = "produto_id"),
			inverseJoinColumns = @JoinColumn(name = "fornecedor_id"))
	@JsonIgnoreProperties("produtos")
	private List<Fornecedor> fornecedores = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProdutoAceitos getProduto() {
		return produto;
	}

	public void setProduto(ProdutoAceitos produto) {
		this.produto = produto;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
}
