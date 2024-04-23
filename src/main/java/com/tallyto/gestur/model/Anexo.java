package com.tallyto.gestur.model;

import jakarta.persistence.*;

@Entity
@Table(name = "anexo")
public class Anexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    private String descricao;

    private String tipo;

    private Long tamanho;
}
