package com.dsantana.minhasfinancas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dsantana.minhasfinancas.model.entity.Lancamento;
import com.dsantana.minhasfinancas.model.enums.StatusLancamento;

@Service
public interface LancamentoService {

	Lancamento salvar(Lancamento lancamento);
	
	Lancamento atualizar(Lancamento lancamento);
	
	void deletar(Lancamento lancamento);
	
	List<Lancamento> buscar(Lancamento lancamentoFiltro);
	
	void atualizarStatus(Lancamento lancamento, StatusLancamento status);
	
	void validar(Lancamento lanamento);
	

	
}
