package com.generation.swaggerino.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "SONO UN UTENTE")
public class User 
{
    @Schema(description = "MI METTO DA SOLO")
    private int id;
    @Schema(description = "Per favore attento mi leggono tutti")
    private String username;
    @Schema(description = "8 caratteri ecc..")
    private String password;
}
