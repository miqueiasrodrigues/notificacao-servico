package com.miqueias.r.notificacao_servico.repository;

import com.miqueias.r.notificacao_servico.domain.Arquivo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArquivoRepository  extends MongoRepository<Arquivo, String> {
    Optional<Arquivo> findByNomeDoArquivo(String nomeDoArquivo);
}
