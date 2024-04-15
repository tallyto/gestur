package com.tallyto.gestur.controller;

import com.tallyto.gestur.model.Venda;
import com.tallyto.gestur.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<Venda> save(@RequestBody Venda venda) {
        Venda novaVenda = vendaService.launchSale(venda);
        return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
    }

    // Outros métodos do controlador para buscar, atualizar, deletar vendas, etc.
}
