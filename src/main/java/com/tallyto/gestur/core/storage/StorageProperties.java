package com.tallyto.gestur.core.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Setter
@Component
@ConfigurationProperties("gestur.storage")
public class StorageProperties {
    private Local local = new Local();
    private S3 s3 = new S3();
    private TipoStorage tipo = TipoStorage.S3;

    public enum TipoStorage {
         S3, LOCAL
    }

    @Getter
    @Setter
    public static class Local {
        private Path diretorioFotos;
    }

    @Getter
    @Setter
    public static class S3 {
        private String idChaveAcesso;
        private String chaveAcessoSecreta;
        private String bucket;
        private String regiao;
        private String diretorioFotos;
    }
}
