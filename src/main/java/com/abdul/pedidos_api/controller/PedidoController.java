package com.abdul.pedidos_api.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abdul.pedidos_api.dto.PedidoRequestDTO;
import com.abdul.pedidos_api.dto.PedidoResponseDTO;
import com.abdul.pedidos_api.model.EstadoPedido;
import com.abdul.pedidos_api.service.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired 
    private PedidoService service;

    @PostMapping 
    public PedidoResponseDTO crear(@Valid @RequestBody PedidoRequestDTO dto){
        return service.crear(dto);
    }

    @GetMapping 
    public Page<PedidoResponseDTO>listarTodos(Pageable pageable){
        return service.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO buscar(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PatchMapping("/{id}/cancelar")
    public PedidoResponseDTO cancelar(@PathVariable Long id){
        return service.cancelar(id);
    }

    @GetMapping("/estado/{estado}")
    public List<PedidoResponseDTO> listarPorEstado(@PathVariable EstadoPedido estado){
        return service.buscarPorEstado(estado);
    }

    @GetMapping("/total-minimo")
    public List<PedidoResponseDTO> listarPorTotalMinimo(@RequestParam double valor){
        return service.listarPorTotalMinimo(valor);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<PedidoResponseDTO>listarPorCliente(@PathVariable Long clienteId){
        return service.buscarPorCliente(clienteId);
    }

    @PatchMapping("/{id}/enviar")
    public PedidoResponseDTO enviar(@PathVariable Long id){
        return service.enviar(id);
    }

    @PatchMapping("/{id}/pagar")
    public PedidoResponseDTO pagar(@PathVariable Long id){
        return service.pagar(id);
    }
}
