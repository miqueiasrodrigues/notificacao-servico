package com.miqueias.r.notificacao_servico.repository;

import com.miqueias.r.notificacao_servico.domain.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    Optional<Arquivo> findByNomeDoArquivo(String nomeDoArquivo);
}
