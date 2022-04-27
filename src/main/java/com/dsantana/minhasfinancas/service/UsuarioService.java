package com.dsantana.minhasfinancas.service;

import org.springframework.stereotype.Service;

import com.dsantana.minhasfinancas.model.entity.Usuario;

@Service
public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
		
}
