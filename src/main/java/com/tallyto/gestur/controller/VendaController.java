package com.tallyto.gestur.controller;

import com.tallyto.gestur.model.ItemVenda;
import com.tallyto.gestur.model.Venda;
import com.tallyto.gestur.model.Produto;
import com.tallyto.gestur.repository.ItemVendaRepository;
import com.tallyto.gestur.service.VendaService;
import com.tallyto.gestur.service.ProdutoService;
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
    private ProdutoService produtoService;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @PostMapping
    public ResponseEntity<Venda> cadastrar(@RequestBody Venda venda) {
        Venda novoVenda = vendaService.cadastrarVenda(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVenda);
    }

    @PostMapping("/{vendaId}/item")
    public ResponseEntity<ItemVenda> adicionarItemPedido(@PathVariable Long vendaId, @RequestBody ItemVenda itemVendaRequest) {
        Venda venda = vendaService.obterPedidoPorId(vendaId);
        if (venda == null) {
            return ResponseEntity.notFound().build();
        }

        Produto produto = produtoService.buscarProduto(itemVendaRequest.getProduto().getId());

        if (produto == null) {
            return ResponseEntity.badRequest().build();
        }

        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setVenda(venda);
        itemVenda.setProduto(produto);
        itemVenda.setQuantidade(itemVendaRequest.getQuantidade());

        // Aqui você pode chamar o serviço para salvar o itemPedido no banco de dados
        itemVenda = itemVendaRepository.save(itemVenda);

        return ResponseEntity.ok(itemVenda);
    }

    @GetMapping
    public ResponseEntity<List<Venda>> listar() {
        List<Venda> vendas = vendaService.listarVendas();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) {
        Venda venda = vendaService.obterPedidoPorId(id);
        return venda != null ? ResponseEntity.ok(venda) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizar(@PathVariable Long id, @RequestBody Venda venda) {
        Venda vendaAtualizado = vendaService.atualizarVenda(id, venda);
        return ResponseEntity.ok(vendaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        vendaService.excluirVenda(id);
        return ResponseEntity.noContent().build();
    }
}
