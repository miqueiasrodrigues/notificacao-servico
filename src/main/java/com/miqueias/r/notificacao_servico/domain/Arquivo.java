package com.miqueias.r.notificacao_servico.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Document(collection = "arquivos")
public class Arquivo  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String notificacaoId;
    private String nomeDoArquivo;
    private String uriDeDownload;
    private String uriDeDeletar;
    private String tipoDeArquivo;
    private long tamanho;

    public Arquivo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotificacaoId() {
        return notificacaoId;
    }

    public void setNotificacaoId(String notificacaoId) {
        this.notificacaoId = notificacaoId;
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

    public String getUriDeDeletar() {
        return uriDeDeletar;
    }

    public void setUriDeDeletar(String uriDeDeletar) {
        this.uriDeDeletar = uriDeDeletar;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arquivo arquivo = (Arquivo) o;
        return tamanho == arquivo.tamanho && Objects.equals(id, arquivo.id) && Objects.equals(notificacaoId, arquivo.notificacaoId) && Objects.equals(nomeDoArquivo, arquivo.nomeDoArquivo) && Objects.equals(uriDeDownload, arquivo.uriDeDownload) && Objects.equals(uriDeDeletar, arquivo.uriDeDeletar) && Objects.equals(tipoDeArquivo, arquivo.tipoDeArquivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notificacaoId, nomeDoArquivo, uriDeDownload, uriDeDeletar, tipoDeArquivo, tamanho);
    }
}

