package com.abdul.pedidos_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdul.pedidos_api.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
