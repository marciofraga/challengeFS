package com.foursales.challengeFS.repositories;

import com.foursales.challengeFS.models.Candidato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    Optional<Candidato> findByCpf(String cpf);

    @Query("FROM Candidato c WHERE LOWER(c.nome) like %?1%")
    Page<Candidato> findByNome(String search, PageRequest pageRequest);
}
