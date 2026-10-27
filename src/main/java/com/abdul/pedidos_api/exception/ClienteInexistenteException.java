package com.abdul.pedidos_api.exception;

public class ClienteInexistenteException extends RuntimeException{
    public ClienteInexistenteException(Long id){
        super("No existe el cliente: "+id);
    }

}
