package com.tallyto.gestur.controller;

import com.tallyto.gestur.model.ItemVenda;
import com.tallyto.gestur.model.Venda;
import com.tallyto.gestur.repository.ItemVendaRepository;
import com.tallyto.gestur.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @PostMapping
    public ResponseEntity<Venda> cadastrar(@RequestBody Venda venda) {
        Venda novoVenda = vendaService.cadastrar(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVenda);
    }

    @PostMapping("/{vendaId}/item")
    public ResponseEntity<ItemVenda> adicionarItemPedido(@PathVariable Long vendaId, @RequestBody ItemVenda itemVenda) {
        Venda venda = vendaService.buscarPorId(vendaId);

        if (venda == null) {
            return ResponseEntity.notFound().build();
        }

        itemVenda.setVenda(venda);

        itemVenda = itemVendaRepository.save(itemVenda);

        return ResponseEntity.ok(itemVenda);
    }

    @GetMapping
    public ResponseEntity<List<Venda>> listar() {
        List<Venda> vendas = vendaService.listar();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) {
        Venda venda = vendaService.buscarPorId(id);
        return venda != null ? ResponseEntity.ok(venda) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizar(@PathVariable Long id, @RequestBody Venda venda) {
        Venda vendaAtualizado = vendaService.atualizar(id, venda);
        return ResponseEntity.ok(vendaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        vendaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
