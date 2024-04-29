package com.tallyto.gestur.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "anexo")
@Getter
@Setter
public class Anexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    @Column(name = "nome_original")
    private String nomeOriginal;

    private String descricao;

    private String tipo;

    private Long tamanho;
}
