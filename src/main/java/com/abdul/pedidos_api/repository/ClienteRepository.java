package com.abdul.pedidos_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdul.pedidos_api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    boolean existsByEmail(String email);
}
