package com.foursales.challengeFS.repositories;

import com.foursales.challengeFS.models.CartaoCredito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {

    @Query("FROM CartaoCredito c WHERE LOWER(c.nomeCartao) like %?1%")
    Page<CartaoCredito> findByNome(String search, PageRequest pageRequest);
}
