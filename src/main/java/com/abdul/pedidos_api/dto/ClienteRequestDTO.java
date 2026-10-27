package com.abdul.pedidos_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteRequestDTO {
    @NotBlank(message = "Debe especificar el nombre del cliente")
    private String nombre;
    @NotBlank(message = "Debe especificar el email del cliente")
    @Email(message = "El email no tiene un formato valido")
    private String email;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
