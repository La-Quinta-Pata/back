package com.laquintapata.app.mapper;

import com.laquintapata.app.dto.request.OriginRequest;
import com.laquintapata.app.dto.response.OriginResponse;
import com.laquintapata.app.entity.Origin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OriginMapper {

    OriginMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(OriginMapper.class);

    OriginResponse originToOriginResponse(Origin origin);

    @Mapping(target = "id", ignore = true)
    Origin originRequestToOrigin(OriginRequest request);
}