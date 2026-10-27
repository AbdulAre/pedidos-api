package com.abdul.pedidos_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdul.pedidos_api.dto.ClienteRequestDTO;
import com.abdul.pedidos_api.dto.ClienteResponseDTO;
import com.abdul.pedidos_api.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @PostMapping
    public ClienteResponseDTO crear(@Valid @RequestBody ClienteRequestDTO dto) {
        return service.crear(dto);
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO buscar(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @GetMapping
    public List<ClienteResponseDTO>listarTodos(){
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto){
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        service.eliminar(id);
    }
}
