package com.miqueias.r.notificacao_servico.domain.dto;

public record ArquivoDTO(
        String id,
        NotificacaoDTO notificacaoDTO,
        String nomeDoArquivo,
        String uriDeDownload,
        String uriDeDeletar,
        String tipoDeArquivo,
        long tamanho) {
}
