package com.miqueias.r.notificacao_servico.service.mapper;


import com.miqueias.r.notificacao_servico.domain.Notificacao;
import com.miqueias.r.notificacao_servico.domain.dto.NotificacaoCreateDTO;
import com.miqueias.r.notificacao_servico.domain.dto.NotificacaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificacaoMapper {
    NotificacaoMapper INSTANCE = Mappers.getMapper(NotificacaoMapper.class);
    NotificacaoDTO toNotificacaoDTO(Notificacao notificacao);
    Notificacao toNotificacao(NotificacaoCreateDTO notificacaoCreateDTO);
    List<NotificacaoDTO> toNotificacoesDTO(List<Notificacao> notificacaos);
}
