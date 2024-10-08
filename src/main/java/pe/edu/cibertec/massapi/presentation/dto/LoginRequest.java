package pe.edu.cibertec.massapi.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Se necesita Email")
    private String email;

    @NotBlank(message = "Se necesita Contraseña")
    private String contrasena;

}
