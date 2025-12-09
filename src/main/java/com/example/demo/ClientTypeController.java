package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client-types")
public class ClientTypeController {
    
    @Autowired
    private ClientTypeService clientTypeService;
    
    @PostMapping
    public ResponseEntity<?> createClientType(@Valid @RequestBody ClientType clientType) {
        try {
            // Validar que el tipo 'business' sea un valor permitido
            if (!"business".equals(clientType.getName()) && !"individual".equals(clientType.getName())) {
                return ResponseEntity.badRequest()
                    .body("Tipo de cliente no válido. Valores permitidos: 'business', 'individual'");
            }
            
            ClientType createdClientType = clientTypeService.createClientType(clientType);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClientType);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor");
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
            return ResponseEntity.notFound().build();
        }
    }
}
