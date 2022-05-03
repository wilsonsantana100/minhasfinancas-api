package com.dsantana.minhasfinancas.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsantana.minhasfinancas.api.dto.LancamentoDTO;
import com.dsantana.minhasfinancas.exception.RegraNegocioException;
import com.dsantana.minhasfinancas.model.entity.Lancamento;
import com.dsantana.minhasfinancas.model.entity.Usuario;
import com.dsantana.minhasfinancas.model.enums.StatusLancamento;
import com.dsantana.minhasfinancas.model.enums.TipoLancamento;
import com.dsantana.minhasfinancas.service.LancamentoService;
import com.dsantana.minhasfinancas.service.UsuarioService;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {
	
	
	@SuppressWarnings("unused")
	private LancamentoService service;
	
	@SuppressWarnings("unused")
	private UsuarioService usuarioService;
	
	
	
	public LancamentoController(LancamentoService service) {
		this.service = service;
	}


	@SuppressWarnings({ "rawtypes" })
	@PostMapping
	public ResponseEntity salvarLancamento(@RequestBody LancamentoDTO dto) {
				
		try {
			Lancamento entidade = converter(dto);
			entidade = service.salvarLancamento(entidade);
			return ResponseEntity.ok(entidade);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
					
	}
	
	
	@SuppressWarnings("rawtypes")
	@PutMapping
	public ResponseEntity atualizar( @PathVariable Long id, @RequestBody LancamentoDTO dto) {
		return null;
		
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
			.orElseThrow( () -> new RegraNegocioException("Usuário não encontrado para o Id informado"));
		
		lancamento.setUsuario(usuario);
		
		lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
		lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
		
		return lancamento;
		
	}
	
}
