package com.foursales.challengeFS.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "candidato")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartaoCredito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCartao;
    private Integer numeroCartao;
    private Integer codigoCartao;

    @ManyToOne(cascade = CascadeType.ALL)
    private Candidato candidato;

    public CartaoCredito withId(Long id) {
        this.id = id;
        return this;
    }

    public CartaoCredito withNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
        return this;
    }

    public CartaoCredito withNumeroCartao(Integer numeroCartao) {
        this.numeroCartao = numeroCartao;
        return this;
    }

    public CartaoCredito withCodigoCartao(Integer codigoCartao) {
        this.codigoCartao = codigoCartao;
        return this;
    }

    public CartaoCredito withCandidato(Candidato candidato) {
        this.candidato = candidato;
        return this;
    }
}