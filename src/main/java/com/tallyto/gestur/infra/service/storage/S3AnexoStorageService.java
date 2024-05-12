package com.tallyto.gestur.infra.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.tallyto.gestur.core.storage.StorageProperties;
import com.tallyto.gestur.service.AnexoStorageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

public class S3AnexoStorageService implements AnexoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;
    @Override
    public AnexoRecuperado recuperar(String nomeArquivo) {
        String caminhoArquivo = getCaminhoArquivo(nomeArquivo);

        URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), caminhoArquivo);

        return AnexoRecuperado.builder().url(url.toString()).build();
    }

    @Override
    public void armazenar(NovoAnexo novoAnexo) {
        try {
            String caminhoArquivo = getCaminhoArquivo(novoAnexo.getNomeArquivo());

            var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(novoAnexo.getTipo());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    caminhoArquivo,
                    novoAnexo.getInputStream(),
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível enviar o arquivo para amazon S3", e);
        }

    }

    private String getCaminhoArquivo(String nomeArquivo) {
        return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            String caminhoArquivo = getCaminhoArquivo(nomeArquivo);

            amazonS3.deleteObject(
                    storageProperties.getS3().getBucket(),
                    caminhoArquivo
            );
        } catch (Exception e) {
            throw new StorageException("Não foi possível remover o arquivo do bucket amazon S3", e);
        }

    }
}
