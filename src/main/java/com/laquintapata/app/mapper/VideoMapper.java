package com.laquintapata.app.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.laquintapata.app.dto.request.VideoRequestDTO;
import com.laquintapata.app.dto.response.VideoResponseDTO;
import com.laquintapata.app.entity.Video;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "axis", ignore = true)
    @Mapping(target = "themes", ignore = true)
    Video toEntity(VideoRequestDTO dto);

    @Mapping(source = "axis.id", target = "axisId")
    @Mapping(source = "axis.type", target = "axisType")
    VideoResponseDTO toResponseDTO(Video video);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "axis", ignore = true)
    @Mapping(target = "themes", ignore = true)
    void updateEntityFromRequest(VideoRequestDTO dto, @MappingTarget Video video);
}
