package com.foursales.challengeFS.services;

import com.foursales.challengeFS.exceptions.ObjectNotFoundException;
import com.foursales.challengeFS.models.CartaoCredito;
import com.foursales.challengeFS.repositories.CartaoCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Optional;

@Service
public class CartaoCreditoService {

    private static final int PAGE = 0;
    private static final int SIZE = 9;

    @Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;

    public Page<CartaoCredito> listar() {
        PageRequest pageRequest = PageRequest.of(PAGE, SIZE, Sort.Direction.ASC, "nomeCartao");
        return Optional.of(cartaoCreditoRepository.findAll(pageRequest))
                .orElseThrow(() -> new ObjectNotFoundException("Nenhum cartao Encontrado"));
    }

    public CartaoCredito buscar(Long id) {
        return cartaoCreditoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("cartao não encontrado"));
    }

    public Page<CartaoCredito> pesquisar(String item, int pagina, int tamanho) {
        PageRequest pageRequest = PageRequest.of(pagina,tamanho, Sort.Direction.ASC, "nomeCartao");
        item = Normalizer.normalize(item, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return cartaoCreditoRepository.findByNome(item.toLowerCase(), pageRequest);
    }

    public CartaoCredito registra(CartaoCredito cartao) {
        cartaoCreditoRepository.findById(cartao.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Existe um candidato cadastrado com esse CPF"));

        return cartaoCreditoRepository.save(cartao);
    }

    public CartaoCredito atualiza(CartaoCredito cartao, Long id) {
        cartaoCreditoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("cartao não encontrado"));

        return cartaoCreditoRepository.save(cartao.withId(id));
    }
}
