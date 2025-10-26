package com.bip.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class BeneficioCadastroDTO {
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private boolean ativo;

}
