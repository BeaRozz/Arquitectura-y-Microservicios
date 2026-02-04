package com.modelo.services.controller;

// import java.util.List;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// // import com.modelo.services.modules.users.dto.CreateUser;
// import com.modelo.services.modules.users.dto.CreateUserResponse;
// import com.modelo.services.modules.users.dto.ErrorCreateUser;
// import com.modelo.services.modules.users.repository.UsuariosRepository;

// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.media.Content;
// import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/home")

@Tag(name = "Home", description = "Endpoints para operaciones básicas del sistema")
public class HomeController {
    
    // private final UsuariosRepository usuariosRepository;

    // public HomeController(UsuariosRepository usuariosRepository) {
    //     this.usuariosRepository = usuariosRepository;
    // }

    // @GetMapping("/hello")
    // @Operation(summary = "Saludo de bienvenida", description = "Retorna un saludo para verificar que el servicio está activo")
    // public String hello(){
    //     return "Hola Spring Boot 🐮";
    // }

    // // @PostMapping("/crear/usuario")
    // // public String postMethodName(@RequestBody CreateUser entity) {

    // //     entity.getAge();
    // //     entity.setAge(30);

    // //     return "Usuario " + entity.getName() + " edad " + entity.getAge();
    // // }

    // @PostMapping("/create/user")
    // @Operation(summary = "Crear nuevo usuario", description = "Recibe datos de usuario, suma 100 años a la edad y confirma la creación.")
    // @ApiResponses(value = {
    //         @ApiResponse(responseCode = "201", description = "Usuario creado correctamente"),
    //         @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
    //                 content = @Content(mediaType = "application/json",
    //                 schema = @Schema(implementation = ErrorCreateUser.class)))
    // })
    
    // public ResponseEntity<CreateUserResponse> createUserPost(@RequestBody CreateUser entity) {
        
    //     String message = "Uusuario creado con éxito";
    //     CreateUser user = CreateUser.builder()
    //         .name(entity.getName() + " creado")
    //         .age(entity.getAge() + 100)
    //         .build();

    //     CreateUserResponse response = CreateUserResponse.builder()
    //         .message(message)
    //         .user(user)
    //         .build();

    //     // return new ResponseEntity<>(response, HttpStatus.CREATED);
    //     return new ResponseEntity<CreateUserResponse>(response, HttpStatus.CREATED);
    // }
    

    // @GetMapping("/users")
    // @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    // public ResponseEntity<List<CreateUser>> getUsers() {
        
    //     List<CreateUser> users = List.of(
    //         CreateUser.builder().name("Alice").age(28).build(),
    //         CreateUser.builder().name("Bob").age(34).build(),
    //         CreateUser.builder().name("Charlie").age(22).build()
    //     );
    //     return new ResponseEntity<List<CreateUser>>(users, HttpStatus.OK);
    // }



    // @GetMapping("/findByUserEmail")
    // public String findByUserEmail() {

    //     String resultado = usuariosRepository.findByEmail("ceviche@test.com")
    //         .map(usuario -> "Usuario encontrado: " + usuario.getName() + " rol: " + usuario.getRole())
    //         .orElse("Usuario no encontrado");

    //     return resultado;
    // }
    
    
}
