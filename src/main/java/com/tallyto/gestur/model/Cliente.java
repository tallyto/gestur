package com.tallyto.gestur.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    @Column(name = "numero_passaporte")
    private String numeroPassaporte;

    @Column(name = "pais_emissao")
    private String paisEmissao;

    @Column(name = "data_emissao")
    private LocalDate dataEmissao;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<AnexoCliente> anexos = new ArrayList<>();

    public void adicionarAnexo(AnexoCliente anexo) {
        anexos.add(anexo);
        anexo.setCliente(this);
    }

    public void removerAnexo(AnexoCliente anexo) {
        anexos.remove(anexo);
    }

}
