package com.laquintapata.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OriginRequest {

    @NotBlank(message = "El nombre del país es obligatorio")
    @Size(max = 50, message = "El nombre del país no puede tener más de 50 caracteres")
    private String country;
}