package com.dsantana.minhasfinancas.service.impl;

import java.time.LocalDate;
import java.util.Optional;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.dsantana.minhasfinancas.exception.ErroAutenticacao;
import com.dsantana.minhasfinancas.exception.RegraNegocioException;
import com.dsantana.minhasfinancas.model.entity.Usuario;
import com.dsantana.minhasfinancas.model.repository.UsuarioRepository;
import com.dsantana.minhasfinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;
	
	
			

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não encontrado para o email informado.");
		}
		
			
		if(!usuario.get().getSenha().equals(senha) ) {
	    	throw new ErroAutenticacao("Senha inválida.");
		}
		
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		
		usuario.setDataCadastro(LocalDate.now());

		return usuarioRepository.save(usuario);
	}
	
	
	@Override
	public void validarEmail(String email) {
		boolean existe = usuarioRepository.existsByEmail(email) ;
		if (existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
		}
		
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		return usuarioRepository.findById(id);
	}

}
