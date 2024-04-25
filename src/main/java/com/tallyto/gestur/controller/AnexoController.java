package com.tallyto.gestur.controller;

import com.tallyto.gestur.model.Anexo;
import com.tallyto.gestur.service.AnexoClienteService;
import com.tallyto.gestur.service.AnexoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/cliente/{clienteId}/anexo")
public class AnexoController {

    @Autowired
    public AnexoClienteService anexoClienteService;

    @Autowired
    AnexoStorageService anexoStorageService;

    @GetMapping("{id}")
    public ResponseEntity<?> recuperar(@PathVariable Long id) {

        try {
            var anexo = anexoClienteService.recuperar(id);

            if (anexo == null) {
                return ResponseEntity.notFound().build();
            }

            var anexoS3 = anexoStorageService.recuperar(anexo.getNomeArquivo());

            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION, anexoS3.getUrl())
                    .build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping()
    public Anexo salvar(@PathVariable Long clienteId, @RequestPart MultipartFile arquivo) throws IOException {
        var anexo = new Anexo();

        anexo.setDescricao(arquivo.getName());
        anexo.setTipo(arquivo.getContentType());
        anexo.setTamanho(arquivo.getSize());
        anexo.setNomeArquivo(arquivo.getOriginalFilename());

        return anexoClienteService.salvar(clienteId, anexo, arquivo.getInputStream());
    }

    @DeleteMapping("{anexoId}")
    public ResponseEntity<Void> remove(@PathVariable Long clienteId, @PathVariable Long anexoId) {
        anexoClienteService.remover(clienteId, anexoId);
        return ResponseEntity.noContent().build();
    }
}
