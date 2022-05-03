package com.dsantana.minhasfinancas.api.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LancamentoDTO {
	
	private Long id;
	
	private String descricao;
	
	private Integer mes;
	
	private Integer ano;
	
	private BigDecimal valor;
	
	private Long usuario;   // vai passar id do usuario e nao o objeto.
	
	private String tipo;
	
	private String status;
	
	
}
