package com.bip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bip.model.Beneficio;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long>{

	public List<Beneficio>findAll();
}
