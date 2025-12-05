package com.laquintapata.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.laquintapata.app.dto.request.VideoRequestDTO;
import com.laquintapata.app.dto.response.CatalogResponseDTO;
import com.laquintapata.app.dto.response.VideoResponseDTO;
import com.laquintapata.app.entity.Theme;
import com.laquintapata.app.entity.Video;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "axis", ignore = true)
    @Mapping(target = "themes", ignore = true)
    @Mapping(target = "migrant", ignore = true)
    @Mapping(target = "user", ignore = true)
    Video toEntity(VideoRequestDTO dto);

    @Mapping(source = "axis.id", target = "axisId")
    @Mapping(source = "axis.type", target = "axisType")
    VideoResponseDTO toResponseDTO(Video video);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "axis", ignore = true)
    @Mapping(target = "themes", ignore = true)
    @Mapping(target = "migrant", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntityFromRequest(VideoRequestDTO dto, @MappingTarget Video video);

    @Mapping(source = "axis.id", target = "axisId")
    @Mapping(source = "axis.type", target = "axisType")
    @Mapping(source = "migrant.id", target = "migrantId")
    @Mapping(source = "migrant.name", target = "migrantName")
    @Mapping(source = "migrant.lastName", target = "migrantLastName")
    @Mapping(source = "migrant.origin.country", target = "migrantOrigin")
    @Mapping(source = "themes", target = "themeNames")
    CatalogResponseDTO toCatalogDTO(Video video);

    default List<String> mapThemes(List<Theme> themes) {
        return themes.stream()
                .map(Theme::getName)
                .collect(Collectors.toList());
    }
}
