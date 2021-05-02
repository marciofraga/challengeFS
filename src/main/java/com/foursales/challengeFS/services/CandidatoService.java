package com.foursales.challengeFS.services;

import com.foursales.challengeFS.exceptions.ObjectNotFoundException;
import com.foursales.challengeFS.models.Candidato;
import com.foursales.challengeFS.repositories.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Optional;

@Service
public class CandidatoService {

    private static final int PAGE = 0;
    private static final int SIZE = 9;

    @Autowired
    private CandidatoRepository candidatoRepository;

    public Candidato atualiza(Candidato candidato, Long id) {
        candidatoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("candidato não encontrado"));

        return candidatoRepository.save(candidato.withId(id));
    }

    public Candidato registra(Candidato candidato) {
        candidatoRepository.findById(candidato.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Existe um candidato cadastrado com esse CPF"));

        return candidatoRepository.save(candidato);
    }

    public Page<Candidato> pesquisar(String item, int pagina, int tamanho) {
        PageRequest pageRequest = PageRequest.of(pagina,tamanho, Sort.Direction.ASC, "nome");
        item = Normalizer.normalize(item, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return candidatoRepository.findByNome(item.toLowerCase(), pageRequest);
    }

    public Candidato buscar(Long id) {
        return candidatoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("candidato não encontrado"));
    }

    public Page<Candidato> listar() {
        PageRequest pageRequest = PageRequest.of(PAGE, SIZE, Sort.Direction.ASC, "nome");
        return Optional.of(candidatoRepository.findAll(pageRequest))
                .orElseThrow(() -> new ObjectNotFoundException("Nenhuma pessoa encontrada"));
    }
}
