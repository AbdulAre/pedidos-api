package com.abdul.pedidos_api.dto;

public class ClienteResponseDTO {
    private Long id;
    private String nombre;
    private String email;

    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }

    public ClienteResponseDTO(Long id, String nombre, String email){
        this.id=id;
        this.nombre=nombre;
        this.email=email;
    }

    
}
