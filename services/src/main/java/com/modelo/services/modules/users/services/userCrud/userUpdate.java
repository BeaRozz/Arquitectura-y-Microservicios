package com.modelo.services.modules.users.services.userCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.modelo.services.modules.users.dto.UserDTO;
import com.modelo.services.modules.users.dto.UserResponse;
import com.modelo.services.modules.users.model.Usuarios;
import com.modelo.services.modules.users.repository.UsuariosRepository;

@Service
public class userUpdate {
    
    @Autowired
    private UsuariosRepository usuariosRepository;

    public ResponseEntity<UserResponse> updateUser(String email, UserDTO userDTO) {
        
        if (repeatedNewEmail(email, userDTO.getEmail())) {
            return ResponseEntity.badRequest().body(
                    UserResponse.builder()
                            .message("El nuevo email ya está registrado")
                            .build()
            );
        }

        try {
            return usuariosRepository.findByEmail(email)
                .map(existingUser -> {
                    existingUser.setName(userDTO.getName());
                    existingUser.setRole(userDTO.getRole());
                    existingUser.setAge(userDTO.getAge());
                    Usuarios updatedUser = usuariosRepository.save(existingUser);

                    UserDTO updatedUserDTO = UserDTO.builder()
                            .name(updatedUser.getName())
                            .email(updatedUser.getEmail())
                            .role(updatedUser.getRole())
                            .age(updatedUser.getAge())
                            .build();

                    return ResponseEntity.ok(
                            UserResponse.builder()
                                    .user(updatedUserDTO)
                                    .message("Usuario actualizado correctamente")
                                    .build()
                    );
                })
                .orElseGet(() -> ResponseEntity.status(404).body(
                        UserResponse.builder()
                                .message("Usuario no encontrado")
                                .build()
                ));

        } catch (Exception e) {

            return ResponseEntity.status(500).body(
                    UserResponse.builder()
                            .message("Error interno del servidor")
                            .build()
            );
        }
    }

    private boolean repeatedNewEmail(String currentEmail, String newEmail) {
        if (currentEmail.equals(newEmail)) {
            return false; // El email no ha cambiado
        }
        // Verificar si el nuevo email ya existe en la base de datos
        return usuariosRepository.findByEmail(newEmail).isPresent();
    }
}
