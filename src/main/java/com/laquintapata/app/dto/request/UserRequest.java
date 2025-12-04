package com.laquintapata.app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no debe tener más de 50 carácteres")
    private String name;

    @NotBlank(message = "Email no puede estar vacio")
    @Email(message = "Email debe ser válido")
    @Size(max = 100, message = "El correo no debe tener más de 100 carácteres")
    private String email;

    private String role;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, max = 10, message = "La contraseña debe tener entre 6 y 10 carácteres")
    private String password;
    
}