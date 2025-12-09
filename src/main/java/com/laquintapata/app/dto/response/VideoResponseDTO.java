package com.laquintapata.app.dto.response;

import java.util.UUID;

import lombok.Data;

@Data
public class VideoResponseDTO {

    private String title;

    private String description;

    private String url;

    private String thumbnailUrl;  
    
    private Long axisId;   
    
    private String axisType; 

    private UUID userId;
}
