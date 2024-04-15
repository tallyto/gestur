package com.tallyto.gestur.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private String telefone;
    private BigDecimal renda;
    private String profissao;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    // Getters e Setters
    // Você pode gerar os getters e setters usando o seu IDE ou Lombok
}
