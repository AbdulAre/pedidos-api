package com.abdul.pedidos_api.exception;

public class EmailRepetidoException extends RuntimeException{
    public EmailRepetidoException(String email){
        super("Ya existe el email: "+email);
    }
}
