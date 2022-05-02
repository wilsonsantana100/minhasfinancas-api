package com.dsantana.minhasfinancas.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsantana.minhasfinancas.api.dto.LancamentoDTO;
import com.dsantana.minhasfinancas.exception.RegraNegocioException;
import com.dsantana.minhasfinancas.model.entity.Lancamento;
import com.dsantana.minhasfinancas.service.LancamentoService;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {
	
	private LancamentoService service;

	
	public LancamentoController(LancamentoService service) {
		this.service = service;
		
	}
		
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity salvarLancamento(@RequestBody LancamentoDTO dto) {
		
		Lancamento lancamento = Lancamento.builder()
				.descricao(dto.getDescricao())
				.mes(dto.getAno())
				.ano(dto.getAno())
				.usuario(dto.getUsuario())
				.dataCadastro(dto.getDataCadastro())
				.tipo(dto.getTipo())
				.status(dto.getStatus()).build();
		
	
	
		try {
			Lancamento lancamentoSalvo = service.salvarLancamento(lancamento);
			return new ResponseEntity(lancamentoSalvo, HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	
	}
	
}
