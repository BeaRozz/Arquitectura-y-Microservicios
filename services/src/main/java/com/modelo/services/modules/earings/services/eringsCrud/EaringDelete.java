package com.modelo.services.modules.earings.services.eringsCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.modelo.services.modules.earings.repository.EringsRepository;

@Service
public class EaringDelete {
    
    @Autowired
    private EringsRepository earingsRepository;

    public ResponseEntity<String> deleteEaring(String model) {
        try {
            return earingsRepository.findByModel(model)
                .map(existingEaring -> {
                    earingsRepository.delete(existingEaring);
                    return ResponseEntity.ok("Arete eliminado correctamente");
                })
                .orElseGet(() -> ResponseEntity.status(404).body("Arete no encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
