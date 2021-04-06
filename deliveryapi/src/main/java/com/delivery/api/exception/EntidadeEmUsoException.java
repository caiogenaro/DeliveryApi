package com.delivery.api.exception;

public class EntidadeEmUsoException extends RuntimeException{
    private static final long serialVersionUID = 1484939409035651600L;


    public EntidadeEmUsoException(String mensagem){
        super(mensagem);
    }
}
