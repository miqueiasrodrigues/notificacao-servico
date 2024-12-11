package com.miqueias.r.notificacao_servico.repository;

import com.miqueias.r.notificacao_servico.domain.Notificacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    @Query("SELECT n FROM Notificacao n WHERE LOWER(n.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    Page<Notificacao> findByTitulo(String titulo, Pageable pageable);
}
