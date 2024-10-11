package com.miqueias.r.notificacao_servico.service.impl;

import com.miqueias.r.notificacao_servico.config.GerenciarArquivoConfig;
import com.miqueias.r.notificacao_servico.exception.FileStorageException;
import com.miqueias.r.notificacao_servico.exception.MyFileNotFoundException;
import com.miqueias.r.notificacao_servico.service.GerenciarArquivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class GerenciarArquivosServiceImpl implements GerenciarArquivosService {
    private final Path localSalvarArquivo;

    @Autowired
    public GerenciarArquivosServiceImpl(GerenciarArquivoConfig gerenciarArquivoConfig) {
        this.localSalvarArquivo = Paths.get(gerenciarArquivoConfig.getUploadDir()).
                toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.localSalvarArquivo);
        } catch (Exception e) {
            throw new FileStorageException("Nao foi possivel criar o diretorio");
        }
    }

    @Override
    public String salvarArquivo(MultipartFile arquivo) {
        String nomeDoArquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());

        try{
           Path targetLocation = this.localSalvarArquivo.resolve(nomeDoArquivo);
           Files.copy(arquivo.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
           return nomeDoArquivo;
        } catch (Exception e) {
            throw new FileStorageException("Nao foi possivel salvar o arquivo no diretorio");
        }
    }

    @Override
    public Resource carregarArquivo(String nomeDoArquivo) {
        try{
            Path caminhoDoArquivo = this.localSalvarArquivo.resolve(nomeDoArquivo).normalize();
            Resource resource = new UrlResource(caminhoDoArquivo.toUri());
            if(resource.exists())
                return resource;
            else
                throw new MyFileNotFoundException("Arquivo não encontrado.");
        } catch (Exception e) {
            throw new MyFileNotFoundException("Arquivo não encontrado.", e);
        }
    }

    @Override
    public boolean deletarArquivo(String nomeDoArquivo) {
        Path caminhoDoArquivo = this.localSalvarArquivo.resolve(nomeDoArquivo).normalize();

        try {
            return Files.deleteIfExists(caminhoDoArquivo);
        } catch (Exception e) {
            throw new MyFileNotFoundException("Arquivo não encontrado.", e);
        }
    }
}
