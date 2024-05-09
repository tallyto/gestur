package com.tallyto.gestur.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "item_venda")
@Getter
@Setter
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @Column(name = "forma_pagamento")
    private String formaPagamento;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    private String anotacao;

    @Column(name = "comissao_recebida")
    private BigDecimal comissaoRecebida;

    @Column(name = "comissao_a_receber")
    private BigDecimal comissaoAReceber;


}
