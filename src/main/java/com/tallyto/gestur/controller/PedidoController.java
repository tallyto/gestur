package com.tallyto.gestur.controller;

import com.tallyto.gestur.model.ItemPedido;
import com.tallyto.gestur.model.Pedido;
import com.tallyto.gestur.model.Produto;
import com.tallyto.gestur.service.PedidoService;
import com.tallyto.gestur.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private  PedidoService pedidoService;

    @Autowired
    private  ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Pedido> cadastrarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.cadastrarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @PostMapping("/{pedidoId}/itens")
    public ResponseEntity<ItemPedido> adicionarItemPedido(@PathVariable Long pedidoId, @RequestBody ItemPedido itemPedidoRequest) {
        Pedido pedido = pedidoService.obterPedidoPorId(pedidoId);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        Produto produto = produtoService.buscarProduto(itemPedidoRequest.getProduto().getId());
        if (produto == null) {
            return ResponseEntity.badRequest().build();
        }

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(itemPedidoRequest.getQuantidade());

        // Aqui você pode chamar o serviço para salvar o itemPedido no banco de dados
        // itemPedido = itemPedidoService.save(itemPedido);

        return ResponseEntity.ok(itemPedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obterPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.obterPedidoPorId(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        Pedido pedidoAtualizado = pedidoService.atualizarPedido(id, pedido);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        pedidoService.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }
}
