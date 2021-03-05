package com.catalogo.App.test.models;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.catalogo.App.models.Fornecedor;

@RunWith(SpringRunner.class)
@DataJpaTest
class FornecedorModelTest {
	
	@Autowired
    private TestEntityManager entityManager;

	@Test
	public void validaSeDoisObjetosPertencemAClaseObject() {
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
		assertNotSame(gustavo, pamela); //Valida se pertence ao mesmo objeto
	}
	@Test
	public void validaSeDoisObjetosSaoIguais() {
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
		assertFalse(gustavo.equals(pamela));
	}
	
	@Test
	public void testaSizeMenorQueDois(){
		
		Fornecedor gustavo = new Fornecedor(
				"G",
				"gustavo@email.com",
				"(19) 9 8172-2419",
				"Rua São Paulo, 265 - Nova Odessa",
				null);
		
		Exception exception = assertThrows(
				ConstraintViolationException.class,
				() -> {
					entityManager.persistAndFlush(gustavo);
					}
				);
		
		String expectedMessage = "Error size";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
}
