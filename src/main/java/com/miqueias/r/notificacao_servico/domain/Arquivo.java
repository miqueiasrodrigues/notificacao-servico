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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arquivo arquivo = (Arquivo) o;
        return Objects.equals(id, arquivo.id) && Objects.equals(notificacaoId, arquivo.notificacaoId) && Objects.equals(nomeDoArquivo, arquivo.nomeDoArquivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notificacaoId, nomeDoArquivo);
    }
}
