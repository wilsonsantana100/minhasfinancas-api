package com.dsantana.minhasfinancas.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsantana.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	// select * from usuario where exists ();
	boolean existsByEmail(String email); // query methods
	
	// select * from usuario where email = email;
	Optional<Usuario> findByEmail(String email);
		
	
	
}
