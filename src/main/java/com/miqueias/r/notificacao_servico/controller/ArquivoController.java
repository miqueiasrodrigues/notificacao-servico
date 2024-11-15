package com.miqueias.r.notificacao_servico.controller;


import com.miqueias.r.notificacao_servico.domain.Arquivo;
import com.miqueias.r.notificacao_servico.domain.dto.ArquivoDTO;
import com.miqueias.r.notificacao_servico.domain.dto.NotificacaoDTO;
import com.miqueias.r.notificacao_servico.service.impl.GerenciarArquivosServiceImpl;
import com.miqueias.r.notificacao_servico.service.impl.NotificacaoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("api/arquivo")
public class ArquivoController {

    @Autowired
    private GerenciarArquivosServiceImpl gerenciarArquivosService;

    @Autowired
    private NotificacaoServiceImpl notificacaoService;

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ArquivoDTO> uploadFile(@RequestParam("arquivo") MultipartFile arquivo,
                                                 @RequestParam("notificacaoId") String notificacaoId) {

        var arquivoDocumentoCreated = gerenciarArquivosService.salvarArquivo(arquivo, notificacaoId);


        var arquivoDTO =  new ArquivoDTO(
                arquivoDocumentoCreated.getId(),
                notificacaoService.findById(arquivoDocumentoCreated.getNotificacaoId()),
                arquivoDocumentoCreated.getNomeDoArquivo(),
                arquivoDocumentoCreated.getUriDeDownload(),
                arquivoDocumentoCreated.getUriDeDeletar(),
                arquivoDocumentoCreated.getTipoDeArquivo(),
                arquivoDocumentoCreated.getTamanho()
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(arquivoDTO);
    }

    @DeleteMapping(value = "/delete/{nomeDoArquivo:.+}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteFile(@PathVariable String nomeDoArquivo) {
       gerenciarArquivosService.deletarArquivo(nomeDoArquivo);
        return ResponseEntity.noContent().build();
    }



    @GetMapping(
            value = "/download/{nomeDoArquivo:.+}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Resource> downloadFile(@PathVariable("nomeDoArquivo") String nomeDoArquivo, HttpServletRequest request) {
        Resource resource = gerenciarArquivosService.carregarArquivo(nomeDoArquivo);
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



}
