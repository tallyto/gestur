package com.tallyto.gestur.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "anexo_venda")
@Getter
@Setter
public class AnexoVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "anexo_id")
    private Anexo anexo;
}
