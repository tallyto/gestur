package com.tallyto.gestur.service;

import com.tallyto.gestur.model.Cliente;
import com.tallyto.gestur.repository.ClienteRepository;
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

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setCpf(clienteAtualizado.getCpf());
        clienteExistente.setRg(clienteAtualizado.getRg());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
        clienteExistente.setRenda(clienteAtualizado.getRenda());
        clienteExistente.setProfissao(clienteAtualizado.getProfissao());
        clienteExistente.setDataNascimento(clienteAtualizado.getDataNascimento());

        clienteExistente.setCep(clienteAtualizado.getCep());
        clienteExistente.setLogradouro(clienteAtualizado.getLogradouro());
        clienteExistente.setComplemento(clienteAtualizado.getComplemento());
        clienteExistente.setCidade(clienteAtualizado.getCidade());
        clienteExistente.setBairro(clienteAtualizado.getBairro());
        clienteExistente.setEstado(clienteAtualizado.getEstado());



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
