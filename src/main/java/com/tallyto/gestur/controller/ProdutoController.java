package com.tallyto.gestur.controller;

import com.tallyto.gestur.model.Produto;
import com.tallyto.gestur.service.ProdutoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/produto")
@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listar() {
        return produtoService.buscarTodosProdutos();
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return produtoService.buscarProduto(id);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        var produtoAtual = produtoService.buscarProduto(id);

        if (produtoAtual == null) {
            return null;
        }

        BeanUtils.copyProperties(produto, produtoAtual, "id");

        return produtoService.atualizarProduto(produtoAtual);
    }
}
