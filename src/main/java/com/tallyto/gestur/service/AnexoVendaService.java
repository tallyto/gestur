package com.tallyto.gestur.service;

import com.tallyto.gestur.model.Anexo;
import com.tallyto.gestur.model.AnexoVenda;
import com.tallyto.gestur.model.Venda;
import com.tallyto.gestur.repository.AnexoRepository;
import com.tallyto.gestur.repository.AnexoVendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class AnexoVendaService {

    @Autowired
    AnexoRepository anexoRepository;

    @Autowired
    AnexoStorageService anexoStorageService;

    @Autowired
    VendaService vendaService;

    @Autowired
    AnexoVendaRepository anexoVendaRepository;

    public Anexo recuperar(Long vendaId, Long anexoId) {
        return anexoVendaRepository.findAnexoVendaByVendaIdAndAnexoId(vendaId, anexoId).getAnexo();
    }

    @Transactional
    public Anexo salvar(Long vendaId, Anexo anexo, InputStream dadosAnexo) {

        Venda venda = vendaService.buscarPorId(vendaId);

        String nomeAnexo = anexoStorageService.gerarNomeArquivo(anexo.getNomeOriginal());

        anexo.setNomeArquivo(nomeAnexo);

        var anexoSalvo = anexoRepository.save(anexo);

        var anexoVenda = new AnexoVenda();

        anexoVenda.setAnexo(anexo);

        venda.adicionarAnexo(anexoVenda);

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

        var clienteAnexo = anexoVendaRepository.findAnexoVendaByVendaIdAndAnexoId(clienteId, anexoId);

        anexoStorageService.remover(clienteAnexo.getAnexo().getNomeArquivo());

        anexoVendaRepository.delete(clienteAnexo);
    }
}
