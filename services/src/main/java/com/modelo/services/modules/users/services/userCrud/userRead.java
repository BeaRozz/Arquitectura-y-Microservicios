package com.modelo.services.modules.users.services.userCrud;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.modelo.services.modules.users.dto.UserDTO;
import com.modelo.services.modules.users.dto.UserListResponse;
import com.modelo.services.modules.users.dto.UserResponse;
import com.modelo.services.modules.users.model.Usuarios;
import com.modelo.services.modules.users.repository.UsuariosRepository;

@Service
public class userRead {
    @Autowired
    private UsuariosRepository usuariosRepository;

    // Obtener todos los usuarios
    public ResponseEntity<UserListResponse> getAllUsers() {
        try {
            List<Usuarios> users = usuariosRepository.findAll();
            if (users.isEmpty()) {
                return new ResponseEntity<>(
                        UserListResponse.builder().message("No se encontraron usuarios").build(),
                        HttpStatus.NOT_FOUND);
            }
            List<UserDTO> userDTOs = users.stream()
                    .map(user -> UserDTO.builder()
                            .name(user.getName())
                            .email(user.getEmail())
                            .role(user.getRole())
                            .age(user.getAge())
                            .build())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(
                    UserListResponse.builder().users(userDTOs).message("Usuarios obtenidos correctamente").build(),
                    HttpStatus.OK);
                    
        } catch (Exception e) {
            // Log de excepción (omitted por brevedad)
            return new ResponseEntity<>(
                    UserListResponse.builder().message("Error interno del servidor").build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<UserResponse> getUserByEmail(String email) {
        try {
            return usuariosRepository.findByEmail(email)
                    .map(user -> ResponseEntity.ok(
                            UserResponse.builder()
                                    .user(UserDTO.builder()
                                            .name(user.getName())
                                            .email(user.getEmail())
                                            .role(user.getRole())
                                            .age(user.getAge())
                                            .build())
                                    .message("Usuario encontrado")
                                    .build()))
                    .orElseGet(() -> new ResponseEntity<>(
                            UserResponse.builder().message("Usuario no encontrado").build(),
                            HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            // Log de excepción (omitted por brevedad)
            return new ResponseEntity<>(
                    UserResponse.builder().message("Error interno del servidor").build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
