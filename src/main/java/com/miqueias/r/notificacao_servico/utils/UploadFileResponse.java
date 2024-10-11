package com.miqueias.r.notificacao_servico.utils;

import java.io.Serial;
import java.io.Serializable;

public class UploadFileResponse implements Serializable {
    @Serial
    private static final long serialVersionUID  = 1L;

    private String nomeDoArquivo;
    private String uriDeDownload;
    private String tipoDeArquivo;
    private long tamanho;

    public UploadFileResponse(String nomeDoArquivo, String uriDeDownload, String tipoDeArquivo, long tamanho) {
        this.nomeDoArquivo = nomeDoArquivo;
        this.uriDeDownload = uriDeDownload;
        this.tipoDeArquivo = tipoDeArquivo;
        this.tamanho = tamanho;
    }

    public String getNomeDoArquivo() {
        return nomeDoArquivo;
    }

    public void setNomeDoArquivo(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
    }

    public String getUriDeDownload() {
        return uriDeDownload;
    }

    public void setUriDeDownload(String uriDeDownload) {
        this.uriDeDownload = uriDeDownload;
    }

    public String getTipoDeArquivo() {
        return tipoDeArquivo;
    }

    public void setTipoDeArquivo(String tipoDeArquivo) {
        this.tipoDeArquivo = tipoDeArquivo;
    }

    public long getTamanho() {
        return tamanho;
    }

    public void setTamanho(long tamanho) {
        this.tamanho = tamanho;
    }
}
