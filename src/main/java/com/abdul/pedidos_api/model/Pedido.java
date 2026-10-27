package com.abdul.pedidos_api.model;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class Pedido {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    @ManyToOne 
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    private double total;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public EstadoPedido getEstado() {
        return estado;
    }
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    
}
