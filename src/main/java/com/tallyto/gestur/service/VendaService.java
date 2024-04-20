package com.tallyto.gestur.service;

import com.tallyto.gestur.model.Venda;
import com.tallyto.gestur.util.GesturUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tallyto.gestur.repository.VendaRepository;

import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public Venda cadastrarVenda(Venda venda) {
        return vendaRepository.save(venda);
    }

    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }

    public Venda obterPedidoPorId(Long id) {
        return vendaRepository.findById(id).orElse(null);
    }

    public Venda atualizarVenda(Long id, Venda venda) {
        var vendaAtual = vendaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Venda não encontrada")
        );

        BeanUtils.copyProperties(venda, vendaAtual, GesturUtils.getNullPropertyNames(venda));

        return vendaRepository.save(vendaAtual);

    }

    public void excluirVenda(Long id) {
        vendaRepository.deleteById(id);
    }
}
