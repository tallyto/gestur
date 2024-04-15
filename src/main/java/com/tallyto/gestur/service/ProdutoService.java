package com.tallyto.gestur.service;

import com.tallyto.gestur.model.Produto;
import com.tallyto.gestur.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository  produtoRepository;

    public Produto salvarProduto(Produto produto){
       return produtoRepository.save(produto);
    }

    public Produto buscarProduto(Long id){
        return produtoRepository.findById(id).orElse(null);
    }

    public List<Produto> buscarTodosProdutos(){
        return produtoRepository.findAll();
    }

    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }

    public Produto atualizarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
}
