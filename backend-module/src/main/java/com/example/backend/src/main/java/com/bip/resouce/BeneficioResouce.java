package com.bip.resouce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.bip.DTO.BeneficioCadastroDTO;
import com.bip.DTO.BeneficioRetornoDTO;
import com.bip.model.Beneficio;
import com.bip.service.BeneficioService;

import java.util.*;

@RestController
@RequestMapping("/api/v1/beneficios")
public class BeneficioResouce {
	
	public BeneficioResouce(BeneficioService beneficioService) {
		this.service = beneficioService;
	}
	
	@Autowired
    private BeneficioService service;

    @GetMapping
    public ResponseEntity<List<BeneficioRetornoDTO>> list() {
        return ResponseEntity.ok(service.listaBeneficios());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Beneficio> beneficioById(@PathVariable Long id) {
        return ResponseEntity.ok(service.detalhaBeneficio(id));
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
    	service.excluirBeneficio(id);
    	return ResponseEntity.noContent().build();
    }
}	
