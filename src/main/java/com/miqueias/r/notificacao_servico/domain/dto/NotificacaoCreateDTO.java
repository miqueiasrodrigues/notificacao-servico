package com.miqueias.r.notificacao_servico.domain.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class NotificacaoCreateDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String conteudo;
    private Integer envolvidos;
    private Date dataHora;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
        NotificacaoCreateDTO that = (NotificacaoCreateDTO) o;
        return Objects.equals(titulo, that.titulo) && Objects.equals(conteudo, that.conteudo) && Objects.equals(envolvidos, that.envolvidos) && Objects.equals(dataHora, that.dataHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, conteudo, envolvidos, dataHora);
    }
}

