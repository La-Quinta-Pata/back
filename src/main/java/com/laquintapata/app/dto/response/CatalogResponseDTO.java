package com.laquintapata.app.dto.response;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class CatalogResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String url;
    private String thumbnailUrl;

    private Long axisId;
    private String axisType;

    private UUID migrantId;
    private String migrantName;
    private String migrantLastName;
    private String migrantOrigin;

    private List<String> themeNames;
}
