package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientTypeService {
    
    @Autowired
    private ClientTypeRepository clientTypeRepository;
    
    public ClientType createClientType(ClientType clientType) {
        if (clientTypeRepository.existsByName(clientType.getName())) {
            throw new IllegalArgumentException("Ya existe un tipo de cliente con el nombre: " + clientType.getName());
        }
        return clientTypeRepository.save(clientType);
    }
    
    public List<ClientType> getAllClientTypes() {
        return clientTypeRepository.findAll();
    }
    
    public ClientType getClientTypeById(Long id) {
        return clientTypeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Tipo de cliente no encontrado con id: " + id));
    }
}
