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

    private String localizador;

    private String atendente;

    private String descricao;

    private String anotacao;

    private Integer quantidade;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    private BigDecimal desconto;

    @Column(name = "comissao_recebida")
    private BigDecimal comissaoRecebida;


    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @Column(name = "quantidade_fornecedor")
    private BigDecimal quantidadeFornecedor;

    @Column(name = "valor_fornecedor")
    private BigDecimal valorFornecedor;

    @Column(name = "desconto_fornecedor")
    private BigDecimal descontoFornecedor;

    @Column(name = "comissao_a_receber")
    private BigDecimal comissaoAReceber;


}
