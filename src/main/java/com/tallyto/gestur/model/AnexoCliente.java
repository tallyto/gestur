package com.tallyto.gestur.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "anexo_cliente")
@Getter
@Setter
public class AnexoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "anexo_id")
    private Anexo anexo;

}
