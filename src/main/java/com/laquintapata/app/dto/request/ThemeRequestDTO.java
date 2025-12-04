package com.laquintapata.app.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ThemeRequestDTO {

    @Size(min = 2, max = 150, message = "El tema debe tener entre 2 y 150 caracteres")
    private String name;

    @Size(max = 500, message = "La describición no puede tener más de 500 caracteres")
    private String description;

}
