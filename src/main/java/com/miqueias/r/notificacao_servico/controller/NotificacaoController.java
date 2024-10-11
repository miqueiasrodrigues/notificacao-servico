package com.miqueias.r.notificacao_servico.controller;


import com.miqueias.r.notificacao_servico.domain.dto.NotificacaoCreateDTO;
import com.miqueias.r.notificacao_servico.domain.dto.NotificacaoDTO;
import com.miqueias.r.notificacao_servico.service.impl.NotificacaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notificacao")
public class NotificacaoController {
    @Autowired
    private NotificacaoServiceImpl service;

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<NotificacaoDTO> findById(@PathVariable(value = "id") String id){
        NotificacaoDTO notificacaoDTO = service.findById(id);
        return ResponseEntity.ok(notificacaoDTO);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Page<NotificacaoDTO>> findByAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(value = "limit", defaultValue = "12") Integer limit,
                                                          @RequestParam(value = "direction", defaultValue = "asc") String direction){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "dataHora"));
        Page<NotificacaoDTO> notificacoesDTO = service.findAll(pageable);
        return ResponseEntity.ok(notificacoesDTO);
    }

    @GetMapping(
            value = "/busca-por-titulo/{titulo}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Page<NotificacaoDTO>> findByAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(value = "limit", defaultValue = "12") Integer limit,
                                                          @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                          @PathVariable(value = "titulo") String titulo){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "dataHora"));
        Page<NotificacaoDTO> notificacoesDTO = service.findAll(titulo, pageable);
        return ResponseEntity.ok(notificacoesDTO);
    }


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<NotificacaoDTO> create(@RequestBody NotificacaoCreateDTO notificacaoCreateDTO){
        NotificacaoDTO notificacaoCreatedDTO = service.create(notificacaoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacaoCreatedDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
