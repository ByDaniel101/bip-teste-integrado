package com.bip.service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.DTO.BeneficioCadastroDTO;
import com.bip.DTO.BeneficioRetornoDTO;
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

	public List<BeneficioRetornoDTO> listaBeneficios() {
		List<Beneficio> beneficios = repository.findAll();
		List<BeneficioRetornoDTO> retorno = beneficios
				.stream()
				.map(mapper::entiryToRetorno)
				.collect(Collectors.toList());
		
		return retorno;
	}

	public Beneficio detalhaBeneficio(long id) {
		Optional<Beneficio> beneficio = repository.findById(id);
		if (beneficio.isPresent()) {
			return beneficio.get();

		} else {
			throw new RuntimeException("Beneficio não encontrado! (id " + id + ")");
		}

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

	public void excluirBeneficio(Long id) {
		Optional<Beneficio> beneficio = repository.findById(id);
		if (beneficio.isPresent()) {
			repository.delete(beneficio.get());
		} else {
			throw new RuntimeException("Beneficio não encontrado! (id " + id + ")");
		}

	}

}
