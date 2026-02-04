package com.modelo.services.modules.users.services.userCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.modelo.services.modules.users.dto.CreateUserDTO;
import com.modelo.services.modules.users.dto.UserDTO;
import com.modelo.services.modules.users.dto.UserResponse;
import com.modelo.services.modules.users.model.Usuarios;
import com.modelo.services.modules.users.repository.UsuariosRepository;

@Service
public class userCreate {

    @Autowired
    private UsuariosRepository usuariosRepository;

    // Crear un nuevo usuario
    public ResponseEntity<UserResponse> createUser(CreateUserDTO createUserDTO) {
        
        if (!completeData(createUserDTO)) {
            return ResponseEntity.badRequest().body(
                    UserResponse.builder()
                            .message("Datos incompletos o inválidos")
                            .build()
            );
        }

        if (emailExists(createUserDTO.getEmail())) {
            return ResponseEntity.badRequest().body(
                    UserResponse.builder()
                            .message("El email ya está registrado")
                            .build()
            );
        }

        try {
            
            Usuarios newUser = Usuarios.builder()
                    .name(createUserDTO.getName())
                    .email(createUserDTO.getEmail())
                    .role(createUserDTO.getRole())
                    .age(createUserDTO.getAge())
                    .build();

            Usuarios savedUser = usuariosRepository.save(newUser);

            UserDTO userDTO = UserDTO.builder()
                    .name(savedUser.getName())
                    .email(savedUser.getEmail())
                    .role(savedUser.getRole())
                    .age(savedUser.getAge())
                    .build();

            return ResponseEntity.ok(
                    UserResponse.builder()
                            .user(userDTO)
                            .message("Usuario creado correctamente")
                            .build()
            );

        } catch (Exception e) {
            
            return ResponseEntity.status(500).body(
                    UserResponse.builder()
                            .message("Error al crear el usuario")
                            .build()
            );
        }
    }

    public boolean emailExists(String email) {
        return usuariosRepository.findByEmail(email).isPresent();
    }

    public boolean completeData(CreateUserDTO createUserDTO) {
        return createUserDTO.getName() != null && createUserDTO.getName().length() >= 3 &&
               createUserDTO.getEmail() != null && !createUserDTO.getEmail().isEmpty() &&
               createUserDTO.getRole() != null && !createUserDTO.getRole().isEmpty() &&
               createUserDTO.getAge() != null && createUserDTO.getAge() >= 18;
    }
    
}
