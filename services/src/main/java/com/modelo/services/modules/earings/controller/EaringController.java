package com.modelo.services.modules.earings.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modelo.services.modules.earings.dto.EaringDTO;
import com.modelo.services.modules.earings.dto.EaringListResponse;
import com.modelo.services.modules.earings.dto.EaringResponse;
import com.modelo.services.modules.earings.services.eringsCrud.EaringCreate;
import com.modelo.services.modules.earings.services.eringsCrud.EaringDelete;
import com.modelo.services.modules.earings.services.eringsCrud.EaringUpdate;
import com.modelo.services.modules.earings.services.eringsCrud.EringsRead;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/earingModule")
@Tag(name = "EaringController", description = "Controlador para la gestión de aretes")
public class EaringController {

    @Autowired
    private EaringDelete earingDeleteService;
    @Autowired
    private EaringUpdate earingUpdateService;
    @Autowired
    private EaringCreate earingCreateService;
    @Autowired
    private EringsRead eringsReadService;
    
    @GetMapping("/")
    @Operation(summary = "Obtiene todos los aretes", description = "Retorna una lista de todos los aretes registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de aretes obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
            @ApiResponse(responseCode = "404", description = "No se encontraron aretes")
    })
    public ResponseEntity<EaringListResponse> getAllEarings() {
        return eringsReadService.getAllEaring();
    }

    @GetMapping("/getByModel")
    @Operation(summary = "Obtiene un arete por modelo", description = "Retorna los detalles de un arete específico según su modelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arete encontrado y retornado correctamente"),
            @ApiResponse(responseCode = "404", description = "Arete no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<EaringResponse> getEaringByModel(@RequestParam String model) {
        return eringsReadService.getEaringByModel(model);
    }
    

    @GetMapping("/createEaring")
    @Operation(summary = "Crea un nuevo arete", description = "Registra un nuevo arete en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Arete creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<EaringResponse> createEaring(@RequestParam EaringDTO earingDTO) {
        return earingCreateService.createEaring(earingDTO);
    }
    
    @GetMapping("/updateEaring/{model}")
    @Operation(summary = "Actualiza un arete existente", description = "Actualiza los detalles de un arete existente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arete actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos"),
            @ApiResponse(responseCode = "404", description = "Arete no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<EaringResponse> updateEaring(@PathVariable String model, @RequestParam EaringDTO earingDTO) {
        return earingUpdateService.updateEaring(model, earingDTO);
    }

    @GetMapping("/deleteEaring/{model}")
    @Operation(summary = "Elimina un arete por modelo", description = "Elimina un arete del sistema según su modelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arete eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Arete no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> deleteEaring(@PathVariable String model) {
        return earingDeleteService.deleteEaring(model);
    }

}
