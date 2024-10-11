package com.miqueias.r.notificacao_servico.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface GerenciarArquivosService {
    String salvarArquivo(MultipartFile arquivo);
    Resource carregarArquivo(String nomeDoArquivo);
    boolean deletarArquivo(String nomeDoArquivo);
}
