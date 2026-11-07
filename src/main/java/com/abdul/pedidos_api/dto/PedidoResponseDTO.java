package com.abdul.pedidos_api.dto;

import java.time.LocalDateTime;

import com.abdul.pedidos_api.model.EstadoPedido;

public class PedidoResponseDTO {
    private Long id;
    private LocalDateTime fecha;
    private EstadoPedido estado;
    private double total;
    private Long clienteId;
    private String clienteNombre;

    public Long getId() {
        return id;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public EstadoPedido getEstado() {
        return estado;
    }
    public double getTotal() {
        return total;
    }
    public Long getClienteId() {
        return clienteId;
    }
    public String getClienteNombre() {
        return clienteNombre;
    }

    public PedidoResponseDTO(Long id, LocalDateTime fecha, EstadoPedido estado, double total, Long clienteId, String clienteNombre){
        this.id=id;
        this.fecha=fecha;
        this.estado=estado;
        this.total=total;
        this.clienteId=clienteId;
        this.clienteNombre=clienteNombre;
    }

    
}
