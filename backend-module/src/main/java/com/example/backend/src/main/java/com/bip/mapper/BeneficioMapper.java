package com.bip.mapper;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.bip.DTO.BeneficioCadastroDTO;
import com.bip.DTO.BeneficioRetornoDTO;
import com.bip.model.Beneficio;

@Component
public class BeneficioMapper {
	

	public Beneficio entradaToEntity(BeneficioCadastroDTO dto) {
		Beneficio entity = new Beneficio();
		
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setAtivo(dto.isAtivo());
		entity.setValor(dto.getValor());
		
		return entity;
	}
	
	public BeneficioRetornoDTO entiryToRetorno(Beneficio entity) {
		BeneficioRetornoDTO dto = new BeneficioRetornoDTO();
		NumberFormat formatoToReal = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setDescricao(entity.getDescricao());
		dto.setAtivo(entity.isAtivo() ? "Ativo" : "Inativo");
		dto.setValor(formatoToReal.format(entity.getValor()));
		
		return dto;
		
	}
}
