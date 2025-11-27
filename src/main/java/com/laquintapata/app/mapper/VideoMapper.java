package com.laquintapata.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.laquintapata.app.dto.request.VideoRequestDTO;
import com.laquintapata.app.dto.response.VideoResponseDTO;
import com.laquintapata.app.entity.Video;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    @Mapping(target = "id", ignore = true)
    Video toEntity (VideoRequestDTO dto);

    VideoResponseDTO toResponseDTO (Video video);    
}
