package com.bip.bip_ear;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;


import java.math.BigDecimal;
import java.util.Objects;

import com.bip.bip_ear.entities.Beneficio;

@Stateless
public class TransferenciaServiceEJB {

    @PersistenceContext(unitName = "beneficio")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void transfer(Long fromId, Long toId, BigDecimal amount) {
        validarParametros(fromId, toId, amount);
        
        Beneficio from = em.find(Beneficio.class, fromId, LockModeType.PESSIMISTIC_WRITE);
        Beneficio to = em.find(Beneficio.class, toId, LockModeType.PESSIMISTIC_WRITE);
        
        if (Objects.isNull(from)) {
            throw new IllegalArgumentException("Benefício de origem não encontrado: " + fromId);
        }
        if (Objects.isNull(to)) {
            throw new IllegalArgumentException("Benefício de destino não encontrado: " + toId);
        }

        if (fromId.equals(toId)) {
            throw new IllegalArgumentException("Não é possível transferir para a mesma conta");
        }
        
        if (from.getValor().compareTo(amount) < 0) {
            throw new IllegalStateException(
                "Saldo insuficiente. Disponível: " + from.getValor() + ", Tentado: " + amount
            );
        }
        
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da transferência deve ser positivo: " + amount);
        }
        
        from.setValor(from.getValor().subtract(amount));
        to.setValor(to.getValor().add(amount));

        em.merge(from);
        em.merge(to);
    }
    
    private void validarParametros(Long fromId, Long toId, BigDecimal amount) {
        if (Objects.isNull(fromId)) {
            throw new IllegalArgumentException("ID de origem não pode ser nulo");
        }
        if (Objects.isNull(toId)) {
            throw new IllegalArgumentException("ID de destino não pode ser nulo");
        }
        if (Objects.isNull(amount)) {
            throw new IllegalArgumentException("Valor não pode ser nulo");
        }
    }

}