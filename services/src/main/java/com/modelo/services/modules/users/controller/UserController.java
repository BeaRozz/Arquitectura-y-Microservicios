package com.modelo.services.modules.users.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modelo.services.modules.users.dto.CreateUserDTO;
import com.modelo.services.modules.users.dto.UserDTO;
import com.modelo.services.modules.users.dto.UserListResponse;
import com.modelo.services.modules.users.dto.UserResponse;
import com.modelo.services.modules.users.services.userCrud.userCreate;
import com.modelo.services.modules.users.services.userCrud.userDelete;
import com.modelo.services.modules.users.services.userCrud.userRead;
import com.modelo.services.modules.users.services.userCrud.userUpdate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/userModule")
@Tag(name = "Usuarios", description = "Maneja el CRUD de usuarios con arquitectura de Monolito Modular")
public class UserController {
    
    @Autowired
    private userRead userReadService;
    
    @Autowired
    private userCreate userCreateService;

    @Autowired
    private userUpdate userUpdateService;

    @Autowired
    private userDelete userDeleteService;

    @GetMapping("/")
    @Operation(summary = "Obtiene todos los usuarios", description = "Retorna una lista de todos los usuarios registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
            @ApiResponse(responseCode = "404", description = "No se encontraron usuarios")
    })
    public ResponseEntity<UserListResponse> getAllUsers() {
        return userReadService.getAllUsers();
    }

    @GetMapping("/getByEmail")
    @Operation(summary = "Obtiene un usuario por email", description = "Retorna los detalles de un usuario específico según su email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<UserResponse> getUserByEmail(@RequestParam String email) {
        return userReadService.getUserByEmail(email);
    }

    @GetMapping("/createUser")
    @Operation(summary = "Crea un nuevo usuario", description = "Crea un nuevo usuario en el sistema con los parámetros proporcionados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<?> createUser(@RequestParam CreateUserDTO createUserDTO) {
        return userCreateService.createUser(createUserDTO);
    }
    
    @GetMapping("/updateUser/{email}")
    @Operation(summary = "Actualiza un usuario por email", description = "Actualiza los detalles de un usuario existente según su email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<UserResponse> updateUser(@PathVariable String email, @RequestParam UserDTO userDTO) {
        return userUpdateService.updateUser(email, userDTO);
    }

    @GetMapping("/deleteUser/{email}")
    @Operation(summary = "Elimina un usuario por email", description = "Elimina un usuario del sistema según su email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        return userDeleteService.deleteUser(email);
    }
    
}
