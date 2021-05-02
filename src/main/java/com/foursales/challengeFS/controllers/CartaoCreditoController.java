package com.foursales.challengeFS.controllers;

import com.foursales.challengeFS.models.CartaoCredito;
import com.foursales.challengeFS.services.CartaoCreditoService;
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
@RequestMapping("/cartaoCredito")
public class CartaoCreditoController {

    @Autowired
    private CartaoCreditoService cartaoCreditoService;

    @GetMapping()
    public ResponseEntity<Page<CartaoCredito>> listar() {
        Page<CartaoCredito> cartao = cartaoCreditoService.listar();
        return ResponseEntity.ok().body(cartao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoCredito> buscar(@PathVariable Long id) {
        CartaoCredito cartao = cartaoCreditoService.buscar(id);
        return ResponseEntity.ok().body(cartao);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CartaoCredito>> pesquisar(
            @RequestParam(value = "searchTerm", required = false, defaultValue = "") String item,
            @RequestParam(value = "page", required = false, defaultValue = "0") int pagina,
            @RequestParam(value = "size", required = false, defaultValue = "9") int tamanho) {
        Page<CartaoCredito> cartoes = cartaoCreditoService.pesquisar(item, pagina, tamanho);
        return ResponseEntity.ok().body(cartoes);
    }

    @PostMapping()
    public ResponseEntity<CartaoCredito> adicionar(@Valid @RequestBody CartaoCredito requestDTO) {
        CartaoCredito cartao = cartaoCreditoService.registra(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cartao.getId())
                .toUri();

        return ResponseEntity.created(uri).body(cartao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartaoCredito> alterar(@Valid @RequestBody CartaoCredito cartaoRequest,
                                             @PathVariable Long id) {
        CartaoCredito cartao = cartaoCreditoService.atualiza(cartaoRequest, id);
        return ResponseEntity.ok().body(cartao);
    }
}
