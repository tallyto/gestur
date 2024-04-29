package com.tallyto.gestur.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface AnexoStorageService {

    AnexoRecuperado recuperar(String nomeArquivo);

    void armazenar(NovoAnexo novoAnexo);

    void remover(String nomeArquivo);

    default String gerarNomeArquivo(String nomeOriginal) {
        return String.format("%s_%s", UUID.randomUUID(), nomeOriginal);
    }

    default void substituir(String nomeArquivoAntigo, NovoAnexo novoAnexo) {
        this.armazenar(novoAnexo);
        if (nomeArquivoAntigo != null) {
            remover(nomeArquivoAntigo);
        }
    }


    @Builder
    @Getter
    class NovoAnexo {
        private String nomeArquivo;
        private InputStream inputStream;
        private String tipo;
    }

    @Builder
    @Getter
    class AnexoRecuperado {
        private String url;

        public boolean temUrl() {
            return url != null;
        }

    }
}
