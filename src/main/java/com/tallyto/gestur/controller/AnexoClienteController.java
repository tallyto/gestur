package com.tallyto.gestur.controller;

import com.tallyto.gestur.model.Anexo;
import com.tallyto.gestur.service.AnexoClienteService;
import com.tallyto.gestur.service.AnexoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/clientes/{clienteId}/anexos")
public class AnexoClienteController {

    @Autowired
    public AnexoClienteService anexoClienteService;

    @Autowired
    AnexoStorageService anexoStorageService;

    @GetMapping("{anexoId}")
    public ResponseEntity<AnexoStorageService.AnexoRecuperado> recuperar(@PathVariable Long clienteId, @PathVariable Long anexoId) {

        try {
            var anexo = anexoClienteService.recuperar(clienteId, anexoId);

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
    public Anexo salvar(@PathVariable Long clienteId, @RequestPart MultipartFile arquivo) throws IOException {
        var anexo = new Anexo();

        anexo.setNomeOriginal(arquivo.getOriginalFilename());
        anexo.setTipo(arquivo.getContentType());
        anexo.setTamanho(arquivo.getSize());

        return anexoClienteService.salvar(clienteId, anexo, arquivo.getInputStream());
    }

    @DeleteMapping("{anexoId}")
    public ResponseEntity<Void> remove(@PathVariable Long clienteId, @PathVariable Long anexoId) {
        anexoClienteService.remover(clienteId, anexoId);
        return ResponseEntity.noContent().build();
    }
}
