package com.modelo.services.modules.earings.services.eringsCrud;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.modelo.services.modules.earings.dto.EaringDTO;
import com.modelo.services.modules.earings.dto.EaringListResponse;
import com.modelo.services.modules.earings.dto.EaringResponse;
import com.modelo.services.modules.earings.model.Erings;
import com.modelo.services.modules.earings.repository.EringsRepository;

@Service
public class EringsRead {
    
    @Autowired
    private EringsRepository earingsRepository;

    public ResponseEntity<EaringListResponse> getAllEaring() {
        try {
            List<Erings> earing = earingsRepository.findAll();
            if (earing.isEmpty()) {
                return new ResponseEntity<>(
                        EaringListResponse.builder().message("No se encontraron aretes").build(),
                        HttpStatus.NOT_FOUND);
            }
            List<EaringDTO> userDTOs = earing.stream()
                    .map(e -> EaringDTO.builder()
                            .model(e.getModel())
                            .color(e.getColor())
                            .size(e.getSize())
                            .edition(e.getEdition())
                            .material(e.getMaterial())
                            .build())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(
                    EaringListResponse.builder().earings(userDTOs).message("Aretes obtenidos correctamente").build(),
                    HttpStatus.OK);
                    
        } catch (Exception e) {
            // Log de excepción (omitted por brevedad)
            return new ResponseEntity<>(
                    EaringListResponse.builder().message("Error interno del servidor").build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<EaringResponse> getEaringByModel(String model) {
        try {
            Erings e = earingsRepository.findByModel(model).orElse(null);
            if (e == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            EaringDTO dto = EaringDTO.builder()
                    .model(e.getModel())
                    .color(e.getColor())
                    .size(e.getSize())
                    .edition(e.getEdition())
                    .material(e.getMaterial())
                    .build();
            return new ResponseEntity<>(EaringResponse.builder().earing(dto).build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
