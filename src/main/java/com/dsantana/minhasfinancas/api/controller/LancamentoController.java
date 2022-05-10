package com.dsantana.minhasfinancas.api.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsantana.minhasfinancas.api.dto.LancamentoDTO;
import com.dsantana.minhasfinancas.exception.RegraNegocioException;
import com.dsantana.minhasfinancas.model.entity.Lancamento;
import com.dsantana.minhasfinancas.model.entity.Usuario;
import com.dsantana.minhasfinancas.model.enums.StatusLancamento;
import com.dsantana.minhasfinancas.model.enums.TipoLancamento;
import com.dsantana.minhasfinancas.service.LancamentoService;
import com.dsantana.minhasfinancas.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lancamentos")
@RequiredArgsConstructor
public class LancamentoController {
	
	
	private final LancamentoService service;
	private final UsuarioService usuarioService;
	
	@SuppressWarnings({ "rawtypes", "unused" })
	@GetMapping
	public ResponseEntity buscar(
			@RequestParam(value ="descricao", required = false) String descricao,
			@RequestParam(value ="mes", required = false) Integer mes,
			@RequestParam(value ="ano", required = false) Integer ano,
			@RequestParam("usuario") Long idUsuario
			) {
				
		Lancamento lancamentoFiltro = new Lancamento();
		lancamentoFiltro.setDescricao(descricao);
		lancamentoFiltro.setMes(mes);
		lancamentoFiltro.setAno(ano);
		
		Optional<Usuario> usuario = usuarioService.obterPorId(idUsuario);
		if (!usuario.isPresent()) {
			return ResponseEntity.badRequest().body("Não foi possível realizar a consulta. Usuário não encontrado para o Id informado.");
		}else {
			lancamentoFiltro.setUsuario(usuario.get());
		}
		
		
		
		
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity salvarLancamento(@RequestBody LancamentoDTO dto) {
		try {
			Lancamento entidade = converter(dto);
			entidade = service.salvarLancamento(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
					
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("{id}")
	public ResponseEntity atualizar( @PathVariable Long id, @RequestBody LancamentoDTO dto) {
		
		return service.obterPorId(id).map( entity -> {
			
			try {
				Lancamento lancamento = converter(dto);
				lancamento.setId(entity.getId());
				service.atualizar(lancamento);
				return ResponseEntity.ok(lancamento);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet( () ->
				new ResponseEntity("Lancamento não encontrado na base de Dados.", HttpStatus.BAD_REQUEST));
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("{id}")
	public ResponseEntity deletar( @PathVariable("id") Long id ) {
		return service.obterPorId(id).map( entidade -> {
			service.deletar(entidade);
			return new ResponseEntity( HttpStatus.NO_CONTENT);
		}).orElseGet( () ->
		new ResponseEntity("Lancamento não encontrado na base de Dados.", HttpStatus.BAD_REQUEST));
		
	}
	
	
	public Lancamento converter(LancamentoDTO dto) {
		
		Lancamento lancamento = new Lancamento();
		
		lancamento.setId(dto.getId());
		lancamento.setDescricao(dto.getDescricao());
		lancamento.setAno(dto.getAno());
		lancamento.setMes(dto.getMes());
		lancamento.setValor(dto.getValor());
		
		
		Usuario usuario = usuarioService
			.obterPorId(dto.getUsuario())
			.orElseThrow( () -> new RegraNegocioException("Usuário não encontrado para o Id informado."));
		
		lancamento.setUsuario(usuario);
		
		if(dto.getTipo() != null) {
			lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
		}
		
		
		if(dto.getStatus() != null) {
			lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
		}
		
		
		return lancamento;
		
	}
	
}
