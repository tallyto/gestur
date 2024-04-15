package com.tallyto.gestur.service;

import com.tallyto.gestur.model.Venda;
import com.tallyto.gestur.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public Venda launchSale(Venda venda) {
        // Implemente aqui a lógica para lançar a venda, se necessário
        return vendaRepository.save(venda);
    }

    // Você pode adicionar mais métodos conforme necessário
}
