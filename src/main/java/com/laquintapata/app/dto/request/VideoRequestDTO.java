package com.laquintapata.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VideoRequestDTO {

    @NotBlank(message = "El título no puede estar vacío.")
    private String title;

    @NotBlank(message = "La descripción no puede estar vacía.")
    private String description;

    @NotBlank(message = "La URL del video es obligatoria.")
    private String url;

    @NotBlank(message = "La URL de la thumbnail es obligatoria.")
    private String thumbnailUrl;   
    
    @NotBlank(message = "El video necesita un eje.")
    private Long axisId;
    
}
