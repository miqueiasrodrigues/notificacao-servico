package com.miqueias.r.notificacao_servico.domain;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "arquivos")
public class Arquivo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long notificacaoId;
    private String nomeDoArquivo;
    private String uriDeDownload;
    private String uriDeDeletar;
    private String tipoDeArquivo;
    private long tamanho;

    public Arquivo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNotificacaoId() {
        return notificacaoId;
    }

    public void setNotificacaoId(Long notificacaoId) {
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
