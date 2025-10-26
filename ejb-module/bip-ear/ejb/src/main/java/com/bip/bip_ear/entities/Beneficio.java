package com.bip.bip_ear.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "beneficio")
public class Beneficio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "valor", precision = 15, scale = 2)
	private BigDecimal valor;

	public Beneficio() {
	}

	public Beneficio(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
