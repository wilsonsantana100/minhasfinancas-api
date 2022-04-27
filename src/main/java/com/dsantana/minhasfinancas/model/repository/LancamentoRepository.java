package com.dsantana.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsantana.minhasfinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
