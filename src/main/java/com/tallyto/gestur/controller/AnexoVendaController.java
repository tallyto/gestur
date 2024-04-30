package com.tallyto.gestur.controller;

import com.tallyto.gestur.model.Anexo;
import com.tallyto.gestur.service.AnexoStorageService;
import com.tallyto.gestur.service.AnexoVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/vendas/{vendaId}/anexos")
public class AnexoVendaController {

    @Autowired
    public AnexoVendaService anexoVendaService;

    @Autowired
    AnexoStorageService anexoStorageService;

    @GetMapping("{anexoId}")
    public ResponseEntity<AnexoStorageService.AnexoRecuperado> recuperar(@PathVariable Long vendaId, @PathVariable Long anexoId) {

        try {
            var anexo = anexoVendaService.recuperar(vendaId, anexoId);

            if (anexo == null) {
                return ResponseEntity.notFound().build();
            }

            var anexoS3 = anexoStorageService.recuperar(anexo.getNomeArquivo());

            return ResponseEntity.ok(anexoS3);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping()
    public Anexo salvar(@PathVariable Long vendaId, @RequestPart MultipartFile arquivo) throws IOException {
        var anexo = new Anexo();

        anexo.setNomeOriginal(arquivo.getOriginalFilename());
        anexo.setTipo(arquivo.getContentType());
        anexo.setTamanho(arquivo.getSize());

        return anexoVendaService.salvar(vendaId, anexo, arquivo.getInputStream());
    }

    @DeleteMapping("{anexoId}")
    public ResponseEntity<Void> remove(@PathVariable Long vendaId, @PathVariable Long anexoId) {
        anexoVendaService.remover(vendaId, anexoId);
        return ResponseEntity.noContent().build();
    }
}
