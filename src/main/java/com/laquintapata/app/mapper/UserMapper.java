package com.laquintapata.app.mapper;

import com.laquintapata.app.dto.request.UserRequest;
import com.laquintapata.app.dto.response.UserResponse;
import com.laquintapata.app.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse userToUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "hashedPassword")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "email", source = "request.email")
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "video", ignore = true)
    User userRequestToUser(UserRequest request, String hashedPassword, String role);
}