package com.abdul.pedidos_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdul.pedidos_api.dto.ClienteRequestDTO;
import com.abdul.pedidos_api.dto.ClienteResponseDTO;
import com.abdul.pedidos_api.exception.ClienteInexistenteException;
import com.abdul.pedidos_api.exception.EmailRepetidoException;
import com.abdul.pedidos_api.model.Cliente;
import com.abdul.pedidos_api.repository.ClienteRepository;

@Service 
public class ClienteService {
    @Autowired 
    private ClienteRepository rep;

    private Cliente buscar(Long id){
        return rep.findById(id).orElseThrow(()->new ClienteInexistenteException(id));
    }

    private ClienteResponseDTO convertirADTO(Cliente cliente){
        return new ClienteResponseDTO(cliente.getId(), cliente.getNombre(), cliente.getEmail());
    }

    public ClienteResponseDTO crear(ClienteRequestDTO dto){
        if(rep.existsByEmail(dto.getEmail())){
            throw new EmailRepetidoException(dto.getEmail());
        }

        Cliente cliente = new Cliente();
        cliente.setEmail(dto.getEmail());
        cliente.setNombre(dto.getNombre());
        Cliente guardado=rep.save(cliente);
        return convertirADTO(guardado);
    }

    public List<ClienteResponseDTO>listarTodos(){
        return rep.findAll().stream().map(this::convertirADTO).toList();
    }

    public ClienteResponseDTO buscarPorId(Long id){
        Cliente cliente = buscar(id);
        return convertirADTO(cliente);
    }

    public ClienteResponseDTO actualizar(Long id, ClienteRequestDTO dto){
        Cliente cliente = buscar(id);
        if(!cliente.getEmail().equals(dto.getEmail()) && rep.existsByEmail(dto.getEmail())){
            throw new EmailRepetidoException(dto.getEmail());
        }
        cliente.setEmail(dto.getEmail());
        cliente.setNombre(dto.getNombre());
        Cliente guardado = rep.save(cliente);
        return convertirADTO(guardado);
    }

    public void eliminar(Long id){
        Cliente cliente = buscar(id);
        rep.delete(cliente);
    }
}
