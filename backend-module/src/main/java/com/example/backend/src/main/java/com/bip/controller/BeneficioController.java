package com.bip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.bip.DTO.BeneficioCadastroDTO;
import com.bip.model.Beneficio;
import com.bip.service.BeneficioService;

import java.util.*;

@RestController
@RequestMapping("/api/v1/beneficios")
public class BeneficioController {
	
	public BeneficioController(BeneficioService beneficioService) {
		this.service = beneficioService;
	}
	
	@Autowired
    private BeneficioService service;

    @GetMapping
    public ResponseEntity<List<String>> list() {
        return ResponseEntity.ok(service.listaBeneficios());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<List<String>> beneficioById() {
        return ResponseEntity.ok(service.listaBeneficios());
    }
    
    @PostMapping("/inserir")
    public ResponseEntity<Beneficio> inserirBeneficio(@Validated @RequestBody BeneficioCadastroDTO dto) {
    	return ResponseEntity.status(HttpStatus.CREATED)
    			.body(service.inserirBeneficio(dto));
    }
    
    @PutMapping("/Atualizar/{id}")
    public ResponseEntity<Beneficio> atualizarBeneficio(@PathVariable Long id,
    		@Validated @RequestBody BeneficioCadastroDTO dto) {
    	
    	return ResponseEntity.status(HttpStatus.CREATED)
    			.body(service.atualizarBeneficio(dto, id));
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<BeneficioCadastroDTO> excluirBeneficio(@PathVariable Long id) {
    	return ResponseEntity.noContent().build();
    }
}	
