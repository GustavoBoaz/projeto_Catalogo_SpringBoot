package com.catalogo.App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catalogo.App.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
