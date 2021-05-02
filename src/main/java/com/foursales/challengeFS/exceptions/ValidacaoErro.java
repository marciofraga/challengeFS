package com.foursales.challengeFS.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoErro extends TemplateErro {
    private static final long serialVersionUID = 1L;

    private List<CampoMensagem> erros = new ArrayList<>();

    public ValidacaoErro(Long timestamp, Integer status, String mensagem) {
        super(status, timestamp, mensagem);
    }

    public List<CampoMensagem> getErros() {
        return erros;
    }

    public void addErro(String nomeCampo, String mensagem) {
        erros.add(new CampoMensagem(nomeCampo, mensagem));
    }
}