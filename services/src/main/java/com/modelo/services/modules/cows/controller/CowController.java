package com.modelo.services.modules.cows.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modelo.services.architecture.response.GeneralResponse;
import com.modelo.services.modules.cows.model.Cows;
import com.modelo.services.modules.cows.repository.CowsRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/cow")
@Tag(name = "Cow", description = "Maneja el CRUD de vacas")
public class CowController {
    
    private final CowsRepository cowsRepository;

    public CowController(CowsRepository cowsRepository) {
        this.cowsRepository = cowsRepository;
    }

    @GetMapping("/")
    @Operation(summary = "Obtener todas las vacas", description = "Retorna una lista de todas las vacas en el sistema")
    public String getCows() {
        return "Lista de vacas";
    }

    @GetMapping("/getByCowById")
    @Operation(summary = "Obtener vaca por ID", description = "Retorna una vaca específica según su ID")
    public String getByCowById(@RequestParam String cowId) {

        String resultado = cowsRepository.findById(cowId)
            .map(cow -> "Vaca encontrada: " + cow.getName() + " edad: " + cow.getAge())
            .orElse("Vaca no encontrada");

        return resultado;
        
    }
    
    @PostMapping("/createCow")
    @Operation(summary = "Crear nueva vaca", description = "Crea una nueva vaca en el sistema con los parámetros proporcionados")
    public ResponseEntity<GeneralResponse<Cows>> createCow(@RequestBody Cows cow) {

        Cows newCows = cowsRepository.save(cow);

        return ResponseEntity.ok(
            new GeneralResponse<Cows>("success", "Vaca creada exitosamente", newCows)
        );
            
    }

    @PutMapping("/updateCow/{id}")
    @Operation(summary = "Actualizar vaca por ID", description = "Actualiza los detalles de una vaca existente según su ID")
    public ResponseEntity<Cows> updateCow(@PathVariable String id, @RequestBody Cows requuest) {
        
        Cows cow = cowsRepository.findById(id).orElseThrow(() -> new RuntimeException("Vaca no encontrada"));
        
        cow.setName(requuest.getName());
        Cows updateCow = cowsRepository.save(cow);
        return new ResponseEntity<Cows>(updateCow, HttpStatus.OK);

    }
    
    @DeleteMapping("/deleteCow/{id}")
    @Operation(summary = "Eliminar vaca", description = "Elimina una vaca del sistema según su ID")
    public ResponseEntity<Void> deleteCow(@PathVariable String id) {
        cowsRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
}
