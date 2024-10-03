package com.miqueias.r.notificacao_servico.service;

import com.miqueias.r.notificacao_servico.domain.dto.NotificacaoCreateDTO;
import com.miqueias.r.notificacao_servico.domain.dto.NotificacaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface NotificacaoService {
    Page<NotificacaoDTO> findAll(Pageable pageable);
    Page<NotificacaoDTO> findAll(String titulo, Pageable pageable);
    NotificacaoDTO findById(String id);
    NotificacaoDTO create(NotificacaoCreateDTO notificacaoCreateDTO);
    void delete(String id);
}
