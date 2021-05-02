package com.foursales.challengeFS.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AddExceptionHandler {

    @ExceptionHandler({ObjectNotFoundException.class})
    public ResponseEntity<TemplateErro> notFound(ObjectNotFoundException e, HttpServletRequest infoRequest) {
        TemplateErro erro = new TemplateErro()
                .withStatus(HttpStatus.NOT_FOUND.value())
                .withTimeStamp(System.currentTimeMillis())
                .withMensagem(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<TemplateErro> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidacaoErro err = new ValidacaoErro(System.currentTimeMillis(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "campos invalidos ou ausentes");

        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addErro(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<TemplateErro> trataErro(final Exception e, final WebRequest request) {
        TemplateErro erro = new TemplateErro()
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withTimeStamp(System.currentTimeMillis())
                .withMensagem(e.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}