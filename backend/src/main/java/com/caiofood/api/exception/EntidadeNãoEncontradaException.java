package com.caiofood.api.exception;

public class EntidadeNãoEncontradaException extends RuntimeException{
    private static final long serialVersionUID = 1484939409035651600L;


    public EntidadeNãoEncontradaException(String mensagem){
        super(mensagem);
    }
}
