package com.miqueias.r.notificacao_servico.domain.dto;

public record CameraDTO(
        Long id,
        String modelo,
        String marca,
        String ip,
        Integer porta,
        String usuario,
        String senha,
        Long usuarioId) {
}

