package com.laquintapata.app.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.laquintapata.app.dto.request.AxisRequestDTO;
import com.laquintapata.app.dto.response.AxisResponseDTO;
import com.laquintapata.app.entity.Axis;

@Mapper(componentModel = "spring")
public interface AxisMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "videos", ignore = true)

    Axis toEntity(AxisRequestDTO dto);

    AxisResponseDTO toResponseDTO(Axis axis);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "videos", ignore = true)
    void updateAxisFromDto(AxisRequestDTO dto, @MappingTarget Axis axis);

}
