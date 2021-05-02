package com.foursales.challengeFS.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CampoMensagem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomeCampo;
    private String mensagem;

    public CampoMensagem withNomeCampo(String nomeCampo) {
        this.nomeCampo = nomeCampo;
        return this;
    }

    public CampoMensagem withMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }
}
