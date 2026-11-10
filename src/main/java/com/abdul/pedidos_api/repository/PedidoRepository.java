package com.abdul.pedidos_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdul.pedidos_api.model.EstadoPedido;
import com.abdul.pedidos_api.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findByClienteId(Long clienteId);
    List<Pedido> findByEstado(EstadoPedido estado);
    List<Pedido> findByTotalGreaterThanEqual(double valor);
}
