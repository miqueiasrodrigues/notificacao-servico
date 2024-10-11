package com.miqueias.r.notificacao_servico.domain;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Document(collection = "notificacaoes")
public class Notificacao implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private Long cameraId;
    private String titulo;
    private String conteudo;
    private Integer envolvidos;

    private Date dataHora;

    public Notificacao() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getCameraId() {
        return cameraId;
    }

    public void setCameraId(Long cameraId) {
        this.cameraId = cameraId;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Integer getEnvolvidos() {
        return envolvidos;
    }

    public void setEnvolvidos(Integer envolvidos) {
        this.envolvidos = envolvidos;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notificacao that = (Notificacao) o;
        return Objects.equals(id, that.id) && Objects.equals(titulo, that.titulo) && Objects.equals(cameraId, that.cameraId) && Objects.equals(conteudo, that.conteudo) && Objects.equals(envolvidos, that.envolvidos) && Objects.equals(dataHora, that.dataHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, cameraId, conteudo, envolvidos, dataHora);
    }
}
