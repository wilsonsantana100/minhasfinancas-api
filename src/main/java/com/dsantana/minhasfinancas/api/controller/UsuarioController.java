package com.dsantana.minhasfinancas.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsantana.minhasfinancas.api.dto.UsuarioDTO;
import com.dsantana.minhasfinancas.exception.RegraNegocioException;
import com.dsantana.minhasfinancas.model.entity.Usuario;
import com.dsantana.minhasfinancas.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	// Endpoints Rest
	
	private UsuarioService service;
	
	
	public UsuarioController( UsuarioService service) {
		this.service = service;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping        
	public ResponseEntity salvarUsuario( @RequestBody UsuarioDTO dto) {
			
		Usuario usuario = Usuario.builder()
				.nome(dto.getNome())
				.email(dto.getEmail())
				.senha(dto.getSenha()).build();
		
		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
			
	}
	
}
