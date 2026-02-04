package com.modelo.services.modules.earings.services.eringsCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.modelo.services.modules.earings.dto.EaringDTO;
import com.modelo.services.modules.earings.dto.EaringResponse;
import com.modelo.services.modules.earings.model.Erings;
import com.modelo.services.modules.earings.repository.EringsRepository;

@Service
public class EaringUpdate {
    
    @Autowired
    private EringsRepository earingsRepository;

    public ResponseEntity<EaringResponse> updateEaring(String model, EaringDTO earingDTO) {

        if (repeatedNewModel(model, earingDTO.getModel())) {
            return ResponseEntity.badRequest().body(
                    EaringResponse.builder()
                            .message("El modelo ya está registrado")
                            .build()
            );
        }

        try {
            return earingsRepository.findByModel(model)
                .map(existingEaring -> {
                    existingEaring.setModel(earingDTO.getModel());
                    existingEaring.setColor(earingDTO.getColor());
                    existingEaring.setSize(earingDTO.getSize());
                    existingEaring.setEdition(earingDTO.getEdition());
                    existingEaring.setMaterial(earingDTO.getMaterial());

                    Erings updatedEaring = earingsRepository.save(existingEaring);
                    
                    EaringDTO updatedEaringDTO = EaringDTO.builder()
                            .model(updatedEaring.getModel())
                           .color(updatedEaring.getColor())
                            .size(updatedEaring.getSize())
                            .edition(updatedEaring.getEdition())
                            .material(updatedEaring.getMaterial())
                            .build();

                    return ResponseEntity.ok(
                            EaringResponse.builder()
                                    .earing(updatedEaringDTO)
                                    .message("Arete actualizado correctamente")
                                    .build()
                    );
                })
                .orElseGet(() -> ResponseEntity.status(404).body(
                        EaringResponse.builder()
                                .message("Arete no encontrado")
                                .build()
                ));

        } catch (Exception e) {

            return ResponseEntity.status(500).body(
                    EaringResponse.builder()
                            .message("Error interno del servidor")
                            .build()
            );
        }
    }

    private boolean repeatedNewModel(String oldModel, String newModel) {
        if (oldModel.equals(newModel)) {
            return false; // El modelo no ha cambiado
        }

        return earingsRepository.findByModel(newModel).isPresent();
    } 
}
