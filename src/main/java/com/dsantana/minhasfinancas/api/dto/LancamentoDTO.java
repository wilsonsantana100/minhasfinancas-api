package com.dsantana.minhasfinancas.api.dto;

import java.time.LocalDate;

import com.dsantana.minhasfinancas.model.entity.Usuario;
import com.dsantana.minhasfinancas.model.enums.StatusLancamento;
import com.dsantana.minhasfinancas.model.enums.TipoLancamento;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LancamentoDTO {
	
	private String descricao;
	
	private Integer mes;
	
	private Integer ano;
	
	private Usuario usuario;
	
	private LocalDate dataCadastro;
	
	private TipoLancamento tipo;
	
	private StatusLancamento status;
	
	
}
