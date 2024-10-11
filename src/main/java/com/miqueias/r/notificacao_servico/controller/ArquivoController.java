package com.miqueias.r.notificacao_servico.controller;


import com.miqueias.r.notificacao_servico.service.impl.GerenciarArquivosServiceImpl;
import com.miqueias.r.notificacao_servico.utils.UploadFileResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/arquivo")
public class ArquivoController {

    @Autowired
    private GerenciarArquivosServiceImpl service;

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UploadFileResponse uploadFile(@RequestParam("arquivo") MultipartFile arquivo) {

        return uploadResponseGenerate(arquivo);
    }

    @PostMapping(
            value = "/upload/multiplos",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<UploadFileResponse> uploadMultipleFile(@RequestParam("arquivos") MultipartFile[] arquivos) {

        return Arrays.asList(arquivos)
                .stream()
                .map(arquivo -> uploadResponseGenerate(arquivo))
                .collect(Collectors.toList());
    }


    @GetMapping(
            value = "/download/{nomeDoArquivo:.+}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Resource> downloadFile(@PathVariable("nomeDoArquivo") String nomeDoArquivo, HttpServletRequest request) {
        Resource resource = service.carregarArquivo(nomeDoArquivo);
        String contentType = "";
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (contentType.isBlank()) contentType = "application/octet-stream";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping(value = "/delete/{nomeDoArquivo:.+}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteFile(@PathVariable String nomeDoArquivo) {

        boolean isDeleted = service.deletarArquivo(nomeDoArquivo);

        return ResponseEntity.ok("Arquivo deletado com sucesso.");
    }


    private UploadFileResponse uploadResponseGenerate(MultipartFile file) {
        var nomeDoArquivo = service.salvarArquivo(file);
        String uriDeDownload = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/arquivo/download/" + nomeDoArquivo)
                .toUriString();
        return new UploadFileResponse(nomeDoArquivo, uriDeDownload, file.getContentType(), file.getSize());
    }

}
