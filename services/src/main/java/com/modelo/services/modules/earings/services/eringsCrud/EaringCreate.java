package com.modelo.services.modules.earings.services.eringsCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.modelo.services.modules.earings.dto.EaringDTO;
import com.modelo.services.modules.earings.dto.EaringResponse;
import com.modelo.services.modules.earings.model.Erings;
import com.modelo.services.modules.earings.repository.EringsRepository;

@Service
public class EaringCreate {
    
    @Autowired
    private EringsRepository earingsRepository;

    public ResponseEntity<EaringResponse> createEaring(EaringDTO earingDTO) {
        
        if (!completeData(earingDTO)) {
            return ResponseEntity.badRequest().body(
                    EaringResponse.builder()
                            .message("Datos incompletos o inválidos")
                            .build()
            );
        }

        if (modelExists(earingDTO.getModel())) {
            return ResponseEntity.badRequest().body(
                    EaringResponse.builder()
                            .message("El modelo ya está registrado")
                            .build()
            );
        }

        try {
            
            Erings newEaring = Erings.builder()
                    .model(earingDTO.getModel())
                    .color(earingDTO.getColor())
                    .size(earingDTO.getSize())
                    .edition(earingDTO.getEdition())
                    .material(earingDTO.getMaterial())
                    .build();

            Erings savedEaring = earingsRepository.save(newEaring);

            EaringDTO earingResponseDTO = EaringDTO.builder()
                    .model(savedEaring.getModel())
                    .color(savedEaring.getColor())
                    .size(savedEaring.getSize())
                    .edition(savedEaring.getEdition())
                    .material(savedEaring.getMaterial())
                    .build();

            return ResponseEntity.ok(
                    EaringResponse.builder()
                            .earing(earingResponseDTO)
                            .message("Arete creado correctamente")
                            .build()
            );

        } catch (Exception e) {
            // Log de excepción (omitted por brevedad)
            return ResponseEntity.status(500).body(
                    EaringResponse.builder()
                            .message("Error interno del servidor")
                            .build()
            );
        }
    }


    public boolean modelExists(String model) {
        return earingsRepository.findByModel(model).isPresent();
    }

    public boolean completeData(EaringDTO earingDTO) {
        return earingDTO.getModel() != null && !earingDTO.getModel().isEmpty()
                && earingDTO.getColor() != null && !earingDTO.getColor().isEmpty()
                && earingDTO.getSize() != null && earingDTO.getSize() > 0
                && earingDTO.getEdition() != null && !earingDTO.getEdition().isEmpty()
                && earingDTO.getMaterial() != null && !earingDTO.getMaterial().isEmpty();
    }

}
