package com.modelo.services.modules.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Respuesta de error al crear un usuario")
public class ErrorCreateUser {
 
    @Schema(example = "400", description = "Código de estado HTTP")
    private int statusCode;

    @Schema(example = "Datos inválidos", description = "Mensaje de error")
    private String message;
}
