package com.tallyto.gestur.service;

import com.tallyto.gestur.model.Cliente;
import com.tallyto.gestur.repository.ClienteRepository;
import com.tallyto.gestur.util.GesturUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        BeanUtils.copyProperties(clienteAtualizado, clienteExistente, GesturUtils.getNullPropertyNames(clienteAtualizado));

        return clienteRepository.save(clienteExistente);
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
    }



    // Você pode adicionar mais métodos conforme necessário
}
