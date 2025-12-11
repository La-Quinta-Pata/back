package com.laquintapata.app.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VideoRequestDTO {

    @NotBlank(message = "El título no puede estar vacío.")
    @Size(max = 100, message = "El titulo no debe tener más de 100 carácteres")
    private String title;

    @NotBlank(message = "La descripción no puede estar vacía.")
    @Size(max = 500, message = "La descripción no debe tener más de 500 carácteres")
    private String description;

    @NotBlank(message = "La URL del video es obligatoria.")
    private String url;

    @NotBlank(message = "La URL de la thumbnail es obligatoria.")
    private String thumbnailUrl;

    @NotNull(message = "El video necesita un eje.")
    private Long axisId;

    @NotNull(message = "El migrante es obligatorio.")
    private UUID migrantId;

}