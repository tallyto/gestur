package com.tallyto.gestur.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_embarque")
    private LocalDate dataEmbarque;

    @Column(name = "data_desembarque")
    private LocalDate dataDesembarque;

    private String origem;
    private String destino;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens = new ArrayList<>();

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<AnexoVenda> anexos = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "servico")
    private Servico servico;

    public void adicionarAnexo(AnexoVenda anexo) {
        anexo.setVenda(this);
        anexos.add(anexo);
    }


}
