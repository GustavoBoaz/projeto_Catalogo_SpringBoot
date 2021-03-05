package com.catalogo.App.services;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.catalogo.App.models.Usuario;
import com.catalogo.App.models.UsuarioLogin;
import com.catalogo.App.repositories.UsuarioRepository;

@Service
public class UsuarioServices {
	
	private @Autowired UsuarioRepository repository;
	
	public Usuario cadastrar(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCriptografada = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return repository.save(usuario);
	}
	
	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> usuarioLogin){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuarioPresente = repository.findByUsuario(usuarioLogin.get().getUsuario());

		if(usuarioPresente.isPresent()) {
			if(encoder.matches(usuarioLogin.get().getSenha(), usuarioPresente.get().getSenha())) {
				String auth = usuarioLogin.get().getUsuario() + ":" + usuarioLogin.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				usuarioLogin.get().setToken(authHeader);				
				usuarioLogin.get().setNome(usuarioPresente.get().getNome());
				usuarioLogin.get().setSenha(usuarioPresente.get().getSenha());

				return usuarioLogin;
			}
		}
		return null;
	}
}
