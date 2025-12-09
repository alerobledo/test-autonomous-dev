package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/client-types")
public class ClientTypeController {
    
    @Autowired
    private ClientTypeService clientTypeService;
    
    @PostMapping
    public ResponseEntity<?> createClientType(@Valid @RequestBody ClientTypeRequest request) {
        try {
            ClientType clientType = new ClientType(request.getName(), request.getDescription());
            ClientType savedClientType = clientTypeService.createClientType(clientType);
            
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedClientType.getId());
            response.put("name", savedClientType.getName());
            response.put("description", savedClientType.getDescription());
            response.put("message", "Tipo de cliente creado exitosamente");
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<ClientType>> getAllClientTypes() {
        List<ClientType> clientTypes = clientTypeService.getAllClientTypes();
        return ResponseEntity.ok(clientTypes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getClientTypeById(@PathVariable Long id) {
        try {
            ClientType clientType = clientTypeService.getClientTypeById(id);
            return ResponseEntity.ok(clientType);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
