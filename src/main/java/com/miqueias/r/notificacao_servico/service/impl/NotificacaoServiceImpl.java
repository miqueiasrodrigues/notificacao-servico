package com.miqueias.r.notificacao_servico.service.impl;

import com.miqueias.r.notificacao_servico.domain.Notificacao;
import com.miqueias.r.notificacao_servico.domain.dto.CameraDTO;
import com.miqueias.r.notificacao_servico.domain.dto.NotificacaoCreateDTO;
import com.miqueias.r.notificacao_servico.domain.dto.NotificacaoDTO;
import com.miqueias.r.notificacao_servico.exception.ResourceNotFoundException;
import com.miqueias.r.notificacao_servico.repository.NotificacaoRepository;
import com.miqueias.r.notificacao_servico.service.GerenciarArquivosService;
import com.miqueias.r.notificacao_servico.service.NotificacaoService;
import com.miqueias.r.notificacao_servico.service.external.CameraClient;
import com.miqueias.r.notificacao_servico.service.mapper.NotificacaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    @Autowired
    private NotificacaoRepository repository;
    @Autowired
    private GerenciarArquivosServiceImpl gerenciarArquivosService;
    @Autowired
    private CameraClient client;

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
        var response = client.findById(notificacaoCreateDTO.getCameraId());

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            Notificacao notificacaoCreated = repository.save(mapper.toNotificacao(notificacaoCreateDTO));
            return mapper.toNotificacaoDTO(notificacaoCreated);
        } else {
            throw new RuntimeException("Erro no microsseviço");
        }
    }


    @Override
    public void delete(String id) {
        Notificacao notificacao = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Notificação não encontrada!")
        );

        repository.delete(notificacao);
    }
}
