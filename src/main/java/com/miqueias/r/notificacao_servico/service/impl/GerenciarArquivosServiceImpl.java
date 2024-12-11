package com.miqueias.r.notificacao_servico.service.impl;

import com.miqueias.r.notificacao_servico.config.GerenciarArquivoConfig;
import com.miqueias.r.notificacao_servico.domain.Arquivo;
import com.miqueias.r.notificacao_servico.domain.dto.ArquivoDTO;
import com.miqueias.r.notificacao_servico.exception.FileStorageException;
import com.miqueias.r.notificacao_servico.exception.MyFileNotFoundException;
import com.miqueias.r.notificacao_servico.repository.ArquivoRepository;
import com.miqueias.r.notificacao_servico.service.GerenciarArquivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class GerenciarArquivosServiceImpl implements GerenciarArquivosService {
    private final Path localSalvarArquivo;

    @Autowired
    public GerenciarArquivosServiceImpl(GerenciarArquivoConfig gerenciarArquivoConfig) {
        this.localSalvarArquivo = Paths.get(gerenciarArquivoConfig.getUploadDir()).
                toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.localSalvarArquivo);
        } catch (Exception e) {
            throw new FileStorageException("Nao foi possivel criar o diretorio");
        }
    }

    @Autowired
    private ArquivoRepository repository;

    @Override
    public ArquivoDTO findById(Long id) {
        return null;
    }

    @Override
    public Arquivo salvarArquivo(MultipartFile arquivo, Long notificacaoId) {


        String nomeDoArquivo = gerarNomeArquivo(
                obterExtensao(StringUtils.cleanPath(arquivo.getOriginalFilename()))
        );

        try {
            Path targetLocation = this.localSalvarArquivo.resolve(nomeDoArquivo);
            Files.copy(arquivo.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            Arquivo arquivoCreate = new Arquivo();

            arquivoCreate.setNomeDoArquivo(nomeDoArquivo);
            arquivoCreate.setNotificacaoId(notificacaoId);

            arquivoCreate.setUriDeDownload(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/arquivo/download/" + nomeDoArquivo)
                    .toUriString());

            arquivoCreate.setUriDeDeletar(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/arquivo/delete/" + nomeDoArquivo)
                    .toUriString());

            arquivoCreate.setTipoDeArquivo(arquivo.getContentType());

            arquivoCreate.setTamanho(arquivo.getSize());

            return repository.save(arquivoCreate);
        } catch (Exception e) {
            throw new FileStorageException("Nao foi possivel salvar o arquivo no diretorio");
        }
    }

    @Override
    public Resource carregarArquivo(String nomeDoArquivo) {
        try {
            Path caminhoDoArquivo = this.localSalvarArquivo.resolve(nomeDoArquivo).normalize();
            Resource resource = new UrlResource(caminhoDoArquivo.toUri());
            if (resource.exists())
                return resource;
            else
                throw new MyFileNotFoundException("Arquivo não encontrado.");
        } catch (Exception e) {
            throw new MyFileNotFoundException("Arquivo não encontrado.", e);
        }
    }

    @Override
    public void deletarArquivo(String nomeDoArquivo) {

        Arquivo arquivo = repository.findByNomeDoArquivo(nomeDoArquivo).orElseThrow(
                () -> new MyFileNotFoundException("Arquivo não encontrado!")
        );

        Path caminhoDoArquivo = this.localSalvarArquivo.resolve(nomeDoArquivo).normalize();

        try {
            var arquivoDeletado = Files.deleteIfExists(caminhoDoArquivo);
            if (arquivoDeletado)
                repository.delete(arquivo);
        } catch (Exception e) {
            throw new MyFileNotFoundException("Arquivo não encontrado.", e);
        }
    }

    private String gerarNomeArquivo(String extensao) {
        String uuid = UUID.randomUUID().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dataFormatada = sdf.format(new Date());

        return uuid + "_" + dataFormatada + extensao;
    }

    private String obterExtensao(String nomeArquivo) {
        if (nomeArquivo == null || nomeArquivo.isEmpty()) {
            return "";
        }
        int lastIndexOfDot = nomeArquivo.lastIndexOf('.');
        return (lastIndexOfDot == -1) ? "" : nomeArquivo.substring(lastIndexOfDot);
    }
}
