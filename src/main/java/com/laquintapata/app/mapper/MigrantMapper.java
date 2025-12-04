package com.laquintapata.app.mapper;

import com.laquintapata.app.dto.request.MigrantRequest;
import com.laquintapata.app.dto.response.MigrantResponse;
import com.laquintapata.app.entity.Migrant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MigrantMapper {

    MigrantMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(MigrantMapper.class);

    MigrantResponse migrantToMigrantResponse(Migrant migrant);

    @Mapping(target = "id", ignore = true)
    Migrant migrantRequestToMigrant(MigrantRequest request);
}