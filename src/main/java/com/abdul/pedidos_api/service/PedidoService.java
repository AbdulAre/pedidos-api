package com.abdul.pedidos_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import com.abdul.pedidos_api.dto.PedidoRequestDTO;
import com.abdul.pedidos_api.dto.PedidoResponseDTO;
import com.abdul.pedidos_api.exception.ClienteInexistenteException;
import com.abdul.pedidos_api.exception.EstadoInvalidoException;
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

    public Page<PedidoResponseDTO>listarTodos(Pageable pageable){
        return pedidoRep.findAll(pageable).map(this::convertirADTO);
    }

    public PedidoResponseDTO buscarPorId(Long id){
        return convertirADTO(buscar(id));
    }

    public PedidoResponseDTO pagar(Long id){
        Pedido pedido = buscar(id);
        if(pedido.getEstado() != EstadoPedido.PENDIENTE){
            throw new EstadoInvalidoException();
        }
        pedido.setEstado(EstadoPedido.PAGADO);
        Pedido guardado = pedidoRep.save(pedido);
        return convertirADTO(guardado);
    }

    public PedidoResponseDTO enviar(Long id){
        Pedido pedido = buscar(id);
        if(pedido.getEstado() != EstadoPedido.PAGADO){
            throw new EstadoInvalidoException();
        }
        pedido.setEstado(EstadoPedido.ENVIADO);
        Pedido guardado = pedidoRep.save(pedido);
        return convertirADTO(guardado);

    }

    public PedidoResponseDTO cancelar(Long id){
        Pedido pedido = buscar(id);
        if(pedido.getEstado() == EstadoPedido.PAGADO || pedido.getEstado() == EstadoPedido.PENDIENTE){
            pedido.setEstado(EstadoPedido.CANCELADO);
            Pedido guardado = pedidoRep.save(pedido);
            return convertirADTO(guardado);
            
        }
        else{
            throw new EstadoInvalidoException();
        }
    }

    public List<PedidoResponseDTO>buscarPorCliente(Long id){
        clienteRep.findById(id).orElseThrow(()->new ClienteInexistenteException(id));
        return pedidoRep.findByClienteId(id).stream().map(this::convertirADTO).toList();

    }

    public List<PedidoResponseDTO>buscarPorEstado(EstadoPedido estado){
        return pedidoRep.findByEstado(estado).stream().map(this::convertirADTO).toList();
    }

    public List<PedidoResponseDTO>listarPorTotalMinimo(double valor){
        return pedidoRep.findByTotalGreaterThanEqual(valor).stream().map(this::convertirADTO).toList();
    }

    private PedidoResponseDTO convertirADTO(Pedido pedido){
        return new PedidoResponseDTO(pedido.getId(), pedido.getFecha(), pedido.getEstado(), pedido.getTotal(), pedido.getCliente().getId(), pedido.getCliente().getNombre());
    }

    private Pedido buscar(Long id){
        return pedidoRep.findById(id).orElseThrow(()->new PedidoInexistenteException(id));
    }
}
