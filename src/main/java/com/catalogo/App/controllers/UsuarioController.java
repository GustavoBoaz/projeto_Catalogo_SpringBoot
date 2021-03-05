package com.catalogo.App.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.App.models.Usuario;
import com.catalogo.App.models.UsuarioLogin;
import com.catalogo.App.services.UsuarioServices;

@RestController
@CrossOrigin("*")
public class UsuarioController {

	private @Autowired UsuarioServices services;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastro(@Valid @RequestBody Usuario usuario){
		return new ResponseEntity<Usuario>(services.cadastrar(usuario), HttpStatus.CREATED);
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> auth(@RequestBody Optional<UsuarioLogin> usuarioLogin){
		return services.logar(usuarioLogin)
				.map(usuario -> ResponseEntity.ok(usuario))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
}
