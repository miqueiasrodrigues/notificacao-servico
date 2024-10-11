package com.miqueias.r.notificacao_servico.service.impl;

import com.miqueias.r.notificacao_servico.domain.Notificacao;
import com.miqueias.r.notificacao_servico.domain.dto.NotificacaoCreateDTO;
import com.miqueias.r.notificacao_servico.domain.dto.NotificacaoDTO;
import com.miqueias.r.notificacao_servico.exception.ResourceNotFoundException;
import com.miqueias.r.notificacao_servico.repository.NotificacaoRepository;
import com.miqueias.r.notificacao_servico.service.GerenciarArquivosService;
import com.miqueias.r.notificacao_servico.service.NotificacaoService;
import com.miqueias.r.notificacao_servico.service.mapper.NotificacaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    @Autowired
    private NotificacaoRepository repository;
    @Autowired
    private GerenciarArquivosServiceImpl gerenciarArquivosService;
    private final NotificacaoMapper mapper = NotificacaoMapper.INSTANCE;

    @Override
    public Page<NotificacaoDTO> findAll(Pageable pageable) {
        var notificacoesPage = repository.findAll(pageable);

        return notificacoesPage.map(p -> mapper.toNotificacaoDTO(p));
    }

    @Override
    public Page<NotificacaoDTO> findAll(String titulo, Pageable pageable) {
        var notificacoesPage = repository.findByTitulo(titulo, pageable);

        return notificacoesPage.map(p -> mapper.toNotificacaoDTO(p));
    }

    @Override
    public NotificacaoDTO findById(String id) {
        Notificacao notificacao = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Notificação não encontrada!")
        );

        return mapper.toNotificacaoDTO(notificacao);
    }

    @Override
    public NotificacaoDTO create(NotificacaoCreateDTO notificacaoCreateDTO) {

        Notificacao notificacaoCreated = repository.save(mapper.toNotificacao(notificacaoCreateDTO));

        return mapper.toNotificacaoDTO(notificacaoCreated);
    }

    @Override
    public void delete(String id) {
        Notificacao notificacao = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Notificação não encontrada!")
        );

        repository.delete(notificacao);
    }
}
