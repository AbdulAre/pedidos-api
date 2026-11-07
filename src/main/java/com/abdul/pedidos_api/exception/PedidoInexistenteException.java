package com.abdul.pedidos_api.exception;

public class PedidoInexistenteException extends RuntimeException{
    public PedidoInexistenteException(Long id){
        super("No se encontro el pedido con el id: "+id);
    }
}
