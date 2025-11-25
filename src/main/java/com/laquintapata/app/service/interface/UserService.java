package com.laquintapata.app.service.interface;
 
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import com.laquintapata.dto.request.UserRequest;
import com.laquintapata.backend.dto.response.UserResponse;

public interface UserService {

    UserResponse createAdmin(UserRequest userRequest);
    UserResponse getUserByName(String name);
    UserResponse getUserById(UUID id);
    List<UserResponse> getUsers();
    UserDetails loadUserByUsername(String username);
}