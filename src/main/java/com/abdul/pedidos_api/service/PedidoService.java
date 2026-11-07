package com.abdul.pedidos_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdul.pedidos_api.dto.PedidoRequestDTO;
import com.abdul.pedidos_api.dto.PedidoResponseDTO;
import com.abdul.pedidos_api.exception.ClienteInexistenteException;
import com.abdul.pedidos_api.exception.PedidoInexistenteException;
import com.abdul.pedidos_api.model.Cliente;
import com.abdul.pedidos_api.model.EstadoPedido;
import com.abdul.pedidos_api.model.Pedido;
import com.abdul.pedidos_api.repository.ClienteRepository;
import com.abdul.pedidos_api.repository.PedidoRepository;

@Service 
public class PedidoService {
    @Autowired 
    private PedidoRepository pedidoRep;

    @Autowired 
    private ClienteRepository clienteRep;

    public PedidoResponseDTO crear(PedidoRequestDTO dto){
        Pedido pedido = new Pedido();
        Cliente cliente = clienteRep.findById(dto.getClienteId()).orElseThrow(()->new ClienteInexistenteException(dto.getClienteId()));
        pedido.setCliente(cliente);
        pedido.setEstado(EstadoPedido.PENDIENTE);
        pedido.setFecha(LocalDateTime.now());
        pedido.setTotal(dto.getTotal());
        Pedido guardado = pedidoRep.save(pedido);

        return convertirADTO(guardado);
    }

    public List<PedidoResponseDTO>listarTodos(){
        return pedidoRep.findAll().stream().map(this::convertirADTO).toList();
    }

    public PedidoResponseDTO buscarPorId(Long id){
        return convertirADTO(buscar(id));
    }

    private PedidoResponseDTO convertirADTO(Pedido pedido){
        return new PedidoResponseDTO(pedido.getId(), pedido.getFecha(), pedido.getEstado(), pedido.getTotal(), pedido.getCliente().getId(), pedido.getCliente().getNombre());
    }

    private Pedido buscar(Long id){
        return pedidoRep.findById(id).orElseThrow(()->new PedidoInexistenteException(id));
    }
}
