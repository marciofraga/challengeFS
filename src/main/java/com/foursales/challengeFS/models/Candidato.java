package com.foursales.challengeFS.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "candidato")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Candidato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Date dataNascimento;
    private String cpf;
    private String endereco;
    private String email;
    private String celular;

    public Candidato withId(Long id) {
        this.id = id;
        return this;
    }

    public Candidato withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Candidato withEmail(String email) {
        this.email = email;
        return this;
    }

    public Candidato withDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public Candidato withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Candidato withEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public Candidato withCelular(String celular) {
        this.celular = celular;
        return this;
    }
}
