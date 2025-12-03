package com.laquintapata.app.service.interfaces;
 
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import com.laquintapata.app.dto.request.UserRequest;
import com.laquintapata.app.dto.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);
    
    
    UserResponse getUserByName(String name);
    UserResponse getUserById(UUID id);
    List<UserResponse> getUsers();
    
    UserResponse updateUser(UUID id, UserRequest userRequest);
    
    void deleteUser(UUID id);
    
    UserDetails loadUserByUsername(String username);
}