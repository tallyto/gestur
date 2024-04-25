package com.tallyto.gestur.service;

import com.tallyto.gestur.model.Anexo;
import com.tallyto.gestur.model.ClienteAnexo;
import com.tallyto.gestur.repository.AnexoRepository;
import com.tallyto.gestur.repository.ClienteAnexoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class AnexoClienteService {

    @Autowired
    ClienteService clienteService;

    @Autowired
    AnexoRepository anexoRepository;

    @Autowired
    AnexoStorageService anexoStorageService;

    @Autowired
    ClienteAnexoRepository  clienteAnexoRepository;

    public Anexo recuperar(Long id) {
        return anexoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Anexo salvar(Long clienteId, Anexo anexo, InputStream dadosAnexo) {

        var cliente = clienteService.buscarClientePorId(clienteId);

        String nomeAnexo = anexoStorageService.gerarNomeArquivo(anexo.getNomeArquivo());

        anexo.setNomeArquivo(nomeAnexo);

        var anexoSalvo = anexoRepository.save(anexo);

        var anexoCliente = new ClienteAnexo();

        anexoCliente.setAnexo(anexo);

        cliente.adicionarAnexo(anexoCliente);

        var uploadAnexo = AnexoStorageService.NovaFoto
                .builder()
                .nomeArquivo(anexo.getNomeArquivo())
                .tipo(anexo.getTipo())
                .inputStream(dadosAnexo)
                .build();

        anexoStorageService.armazenar(uploadAnexo);

        return anexoSalvo;
    }

    @Transactional
    public void remover(Long clienteId, Long anexoId) {

        // TODO: Remover anexo ao remover cliente anexo em cascata
        var clienteAnexo = clienteAnexoRepository.findClienteAnexoByClienteIdAndAnexoId(clienteId, anexoId);

        clienteAnexoRepository.delete(clienteAnexo);

        anexoRepository.delete(clienteAnexo.getAnexo());

        anexoStorageService.remover(clienteAnexo.getAnexo().getNomeArquivo());


    }
}
