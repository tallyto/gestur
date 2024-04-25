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

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<ClienteAnexo> anexos = new ArrayList<>();

    public void adicionarAnexo(ClienteAnexo anexo) {
        anexos.add(anexo);
        anexo.setCliente(this);
    }

    public void removerAnexo(ClienteAnexo anexo) {
        anexos.remove(anexo);
    }

}
