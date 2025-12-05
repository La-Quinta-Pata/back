package com.laquintapata.app.dto.response;

import java.util.List;

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

    private Long migrantId;
    private String migrantName;
    private String migrantLastName;
    private String migrantOrigin;

    private List<String> themeNames;
}
