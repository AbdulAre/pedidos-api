package com.abdul.pedidos_api.exception;

public class ClienteConPedidosException extends RuntimeException{
    public ClienteConPedidosException(Long id){
        super("El cliente "+id+" aun tiene pedidos");
    }
}
