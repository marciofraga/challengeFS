package com.foursales.challengeFS.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateErro implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;
    private Long timeStamp;
    private String mensagem;

    public TemplateErro withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public TemplateErro withTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public TemplateErro withMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }

    public String toString() {
        return "{\n\"status\": \"" + status + "\", \n \"timeStamp\": \"" + timeStamp + "\", \n \"mensagem\": \"" + mensagem + "\"\n}";
    }
}
