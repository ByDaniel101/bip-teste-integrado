package com.bip.service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.DTO.BeneficioCadastroDTO;
import com.bip.mapper.BeneficioMapper;
import com.bip.model.Beneficio;
import com.bip.repository.BeneficioRepository;

@Service
public class BeneficioService {

	@Autowired
	private BeneficioRepository repository;

	@Autowired
	private BeneficioMapper mapper;

	public BeneficioService(BeneficioRepository beneficioRepository, BeneficioMapper beneficioMapper) {
		this.repository = beneficioRepository;
		this.mapper = beneficioMapper;

	}

	public List<String> listaBeneficios() {
		List<String> retorno = new ArrayList<>();
		List<Beneficio> beneficios = repository.findAll();
		
		beneficios.forEach(b -> b.getNome());

		return retorno;
	}

	@Transient
	public Beneficio inserirBeneficio(BeneficioCadastroDTO dto) {
		return repository.save(mapper.entradaToEntity(dto));
	}

	@Transient
	public Beneficio atualizarBeneficio(BeneficioCadastroDTO dto, Long id) {
		Beneficio beneficio = mapper.entradaToEntity(dto);
		beneficio.setId(id);
		return repository.save(beneficio);
	}

	public void ExcluirBeneficio(Long id) {
		Optional<Beneficio> beneficio = repository.findById(id);
		if (beneficio.isPresent()) {
			repository.delete(beneficio.get());
		} else {
			throw new RuntimeException("Beneficio não encontrado! (id " + id + ")");
		}

	}

}
