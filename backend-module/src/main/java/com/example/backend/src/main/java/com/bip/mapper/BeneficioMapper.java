package com.bip.mapper;

import org.springframework.stereotype.Component;

import com.bip.DTO.BeneficioCadastroDTO;
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
}
