package com.tallyto.gestur.service;

import com.tallyto.gestur.model.Fornecedor;
import com.tallyto.gestur.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor salvar(Fornecedor fornecedor) {
       return fornecedorRepository.save(fornecedor);
    }

    public void deletar(Long id) {
        fornecedorRepository.deleteById(id);
    }

    public Fornecedor atualizar(Long id, Fornecedor fornecedorAtualizado) {
        var fornecedorAtual = buscarPorId(id);
        if (fornecedorAtual == null) return null;
        fornecedorAtual.setNome(fornecedorAtualizado.getNome());

        return fornecedorRepository.save(fornecedorAtual);
    }

    public List<Fornecedor> listar() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        return fornecedorRepository.findById(id).orElse(null);
    }
}
