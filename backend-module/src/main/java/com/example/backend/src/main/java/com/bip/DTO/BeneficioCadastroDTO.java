package com.bip.DTO;

import lombok.Data;

@Data
public class BeneficioCadastroDTO {
	private String nome;
	private String descricao;
	private float valor;
	private boolean ativo;

}
