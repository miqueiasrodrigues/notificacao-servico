package com.miqueias.r.notificacao_servico.service;

import com.miqueias.r.notificacao_servico.domain.Arquivo;
import com.miqueias.r.notificacao_servico.domain.dto.ArquivoDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface GerenciarArquivosService {
    ArquivoDTO findById(String id);
    Arquivo salvarArquivo(MultipartFile arquivo, String notificacaoId);
    Resource carregarArquivo(String nomeDoArquivo);
    void deletarArquivo(String nomeDoArquivo);
}
