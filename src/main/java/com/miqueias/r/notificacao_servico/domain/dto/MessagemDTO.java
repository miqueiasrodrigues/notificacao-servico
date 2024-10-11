package com.miqueias.r.notificacao_servico.domain.dto;

import java.time.LocalDateTime;

public record MessagemDTO(
        Integer envolvidos,
        LocalDateTime dataHora) {
}
