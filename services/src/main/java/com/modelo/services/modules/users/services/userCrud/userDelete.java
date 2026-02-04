package com.modelo.services.modules.users.services.userCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.modelo.services.modules.users.repository.UsuariosRepository;

@Service
public class userDelete {
    
    @Autowired
    private UsuariosRepository usuariosRepository;

    public ResponseEntity<String> deleteUser(String email) {
        try {
            return usuariosRepository.findByEmail(email)
                .map(existingUser -> {
                    usuariosRepository.delete(existingUser);
                    return ResponseEntity.ok("Usuario eliminado correctamente");
                })
                .orElseGet(() -> ResponseEntity.status(404).body("Usuario no encontrado"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
