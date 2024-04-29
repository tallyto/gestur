package com.tallyto.gestur.service;

import com.tallyto.gestur.model.Anexo;
import com.tallyto.gestur.model.AnexoCliente;
import com.tallyto.gestur.repository.AnexoRepository;
import com.tallyto.gestur.repository.AnexoClienteRepository;
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
    AnexoClienteRepository anexoClienteRepository;

    public Anexo recuperar(Long clienteId, Long anexoId) {
        return anexoClienteRepository.findAnexoClienteByClienteIdAndAnexoId(clienteId, anexoId).getAnexo();
    }

    @Transactional
    public Anexo salvar(Long clienteId, Anexo anexo, InputStream dadosAnexo) {

        var cliente = clienteService.buscarClientePorId(clienteId);

        String nomeAnexo = anexoStorageService.gerarNomeArquivo(anexo.getNomeOriginal());

        anexo.setNomeArquivo(nomeAnexo);

        var anexoSalvo = anexoRepository.save(anexo);

        var anexoCliente = new AnexoCliente();

        anexoCliente.setAnexo(anexo);

        cliente.adicionarAnexo(anexoCliente);

        var uploadAnexo = AnexoStorageService.NovoAnexo
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

        var clienteAnexo = anexoClienteRepository.findAnexoClienteByClienteIdAndAnexoId(clienteId, anexoId);

        anexoClienteRepository.delete(clienteAnexo);

        anexoStorageService.remover(clienteAnexo.getAnexo().getNomeArquivo());

    }
}
