package com.tallyto.gestur.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "venda")
@Getter
@Setter
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    @Column(name = "valor_repassado")
    private BigDecimal valorRepassado;

    @Column(name = "forma_pagamento")
    private String formaPagamento;

    @Column(name = "comissao_rav")
    private BigDecimal valorComissaoRAV;

    @Column(name = "comissao_markup")
    private BigDecimal valorComissaoMarkup;

    @Column(name = "desconto_aplicado")
    private boolean descontoAplicado;

    // Getters e Setters
}
