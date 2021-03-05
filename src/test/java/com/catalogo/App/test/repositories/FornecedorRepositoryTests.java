package com.catalogo.App.test.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.catalogo.App.models.Fornecedor;
import com.catalogo.App.repositories.FornecedorRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
class FornecedorRepositoryTests {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
    private FornecedorRepository repository;

	@Test
	public void procuraFornecedorPorId_RetornaFornecedor(){
		Fornecedor gustavo = new Fornecedor(
				"Gustavo",
				"gustavo@email.com",
				"(19) 9 8172-2419",
				"Rua São Paulo, 265 - Nova Odessa",
				null);
		entityManager.persistAndFlush(gustavo);
		
		Fornecedor resultado = repository.findById(1L).get();
		
		assertEquals(gustavo, resultado); //Valida se o retorno é igual a entidade criada	
	}
	
	@Test
	public void procuraFornecedorPorNome_RetornaListaDeFornecedores() {
		Fornecedor gustavo = new Fornecedor(
				"Gustavo Boaz",
				"gustavo@email.com",
				"(19) 9 8172-2419",
				"Rua São Paulo, 265 - Nova Odessa",
				null);
		Fornecedor pamela = new Fornecedor(
				"Pamela Boaz",
				"pamela@email.com",
				"(19) 9 8172-2419",
				"Rua São Paulo, 265 - Nova Odessa",
				null);
		entityManager.persistAndFlush(gustavo);
		entityManager.persistAndFlush(pamela);
		
		List<Fornecedor> resultado = repository.findByNomeContaining("");
		assertNotNull(resultado); // Valuda retorno Nulo
	}
	
	@Test
	public void procuraFornecedorPorNome_RetornaListaDeFornecedoresValidandoOTamanho() {
		Fornecedor gustavo = new Fornecedor(
				"Gustavo Boaz",
				"gustavo@email.com",
				"(19) 9 8172-2419",
				"Rua São Paulo, 265 - Nova Odessa",
				null);
		Fornecedor pamela = new Fornecedor(
				"Pamela Boaz",
				"pamela@email.com",
				"(19) 9 8172-2419",
				"Rua São Paulo, 265 - Nova Odessa",
				null);
		entityManager.persistAndFlush(gustavo);
		entityManager.persistAndFlush(pamela);
		
		List<Fornecedor> resultado = repository.findByNomeContaining("");
		assertEquals(2, resultado.size()); // Valuda retorno Nulo
	}
}
