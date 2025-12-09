package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClientTypeRequest {
    
    @NotBlank(message = "El nombre del tipo de cliente es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    @Pattern(regexp = "^(business|individual|premium)$", message = "El tipo de cliente debe ser: business, individual o premium")
    private String name;
    
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    private String description;
    
    // Constructores
    public ClientTypeRequest() {}
    
    public ClientTypeRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    // Getters y Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
