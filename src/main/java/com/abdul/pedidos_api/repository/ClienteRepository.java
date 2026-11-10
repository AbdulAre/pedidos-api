package com.abdul.pedidos_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdul.pedidos_api.model.Cliente;
import com.abdul.pedidos_api.model.Pedido;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    boolean existsByEmail(String email);
}
