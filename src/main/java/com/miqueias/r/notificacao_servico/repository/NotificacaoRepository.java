package com.miqueias.r.notificacao_servico.repository;

import com.miqueias.r.notificacao_servico.domain.Notificacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends MongoRepository<Notificacao, String> {
//    @Query("SELECT n FROM Notificacao n WHERE LOWER(n.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
//    Page<Notificacao> findByTitulo(@Param("titulo") String titulo, Pageable pageable);

    @Query("{ 'titulo': { $regex: ?0, $options: 'i' } }")
    Page<Notificacao> findByTitulo(String titulo, Pageable pageable);

}
