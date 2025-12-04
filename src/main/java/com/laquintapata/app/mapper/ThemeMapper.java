package com.laquintapata.app.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.laquintapata.app.dto.request.ThemeRequestDTO;
import com.laquintapata.app.dto.response.ThemeResponseDTO;
import com.laquintapata.app.entity.Theme;

@Mapper(componentModel = "spring")
public interface ThemeMapper {

    @Mapping(target="id", ignore = true)
    Theme toEntity(ThemeRequestDTO dto);

    ThemeResponseDTO toResponseDTO(Theme theme);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="id", ignore = true)
    void updateEntityFromRequest(ThemeRequestDTO dto, @MappingTarget Theme theme);    
}
