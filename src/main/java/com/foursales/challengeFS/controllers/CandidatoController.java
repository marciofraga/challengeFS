package com.foursales.challengeFS.controllers;

import com.foursales.challengeFS.models.Candidato;
import com.foursales.challengeFS.services.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @GetMapping()
    public ResponseEntity<Page<Candidato>> listar() {
        Page<Candidato> candidatos = candidatoService.listar();
        return ResponseEntity.ok().body(candidatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidato> buscar(@PathVariable Long id) {
        Candidato candidato = candidatoService.buscar(id);
        return ResponseEntity.ok().body(candidato);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Candidato>> pesquisar(
            @RequestParam(value = "searchTerm", required = false, defaultValue = "") String item,
            @RequestParam(value = "page", required = false, defaultValue = "0") int pagina,
            @RequestParam(value = "size", required = false, defaultValue = "9") int tamanho) {
        Page<Candidato> candidatos = candidatoService.pesquisar(item, pagina, tamanho);
        return ResponseEntity.ok().body(candidatos);
    }

    @PostMapping()
    public ResponseEntity<Candidato> adicionar(@Valid @RequestBody Candidato candidatoRequest) {
        Candidato candidato = candidatoService.registra(candidatoRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(candidato.getId())
                .toUri();

        return ResponseEntity.created(uri).body(candidato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidato> alterar(@Valid @RequestBody Candidato candidatoRequest,
                                                     @PathVariable Long id) {
        Candidato candidato = candidatoService.atualiza(candidatoRequest, id);
        return ResponseEntity.ok().body(candidato);
    }
}
