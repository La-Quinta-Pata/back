package com.laquintapata.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AxisRequestDTO {

    @NotBlank(message = "El tipo no puede estar vacío.")
    @Size(min = 2, max = 50, message = "El eje debe tener entre 2 y 50 caracteres")
    private String type;
}
