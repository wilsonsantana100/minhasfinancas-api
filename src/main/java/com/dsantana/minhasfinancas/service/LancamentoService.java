package com.dsantana.minhasfinancas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dsantana.minhasfinancas.model.entity.Lancamento;
import com.dsantana.minhasfinancas.model.enums.StatusLancamento;

@Service
public interface LancamentoService {

		
	Lancamento atualizar(Lancamento lancamento);
	
	void deletar(Lancamento lancamento);
	
	List<Lancamento> buscar(Lancamento lancamentoFiltro);
	
	void atualizarStatus(Lancamento lancamento, StatusLancamento status);
	
	void validar(Lancamento lanamento);

	Lancamento salvarLancamento(Lancamento lancamento);
	
	Optional<Lancamento> obterPorId(Long id);
	
	
	BigDecimal obterSaldoPorUsuario(Long id);
	
}
