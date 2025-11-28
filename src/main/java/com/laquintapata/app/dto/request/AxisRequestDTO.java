package com.laquintapata.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AxisRequestDTO {
    
    @NotBlank(message = "El tipo no puede estar vacío.")
    private String type;
}
