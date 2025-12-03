package com.laquintapata.app.service.impl;


import com.laquintapata.app.repository.UserRepository;
import com.laquintapata.app.service.interfaces.UserService;
import com.laquintapata.app.mapper.UserMapper;
import com.laquintapata.app.entity.User;
import com.laquintapata.app.dto.request.UserRequest;
import com.laquintapata.app.dto.response.UserResponse;
import com.laquintapata.app.exception.ResourceNotFoundException;
import com.laquintapata.app.exception.DuplicateResourceException;
import com.laquintapata.app.security.UserDetail;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new UsernameNotFoundException("Usuario no encontrado con email: " + email)
            );
        return new UserDetail(user);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new DuplicateResourceException(
                "El email '" + userRequest.getEmail() + "' ya está registrado."
            );
        }
        
        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());
        
        String role = (userRequest.getRole() != null && !userRequest.getRole().isEmpty()) 
                      ? userRequest.getRole() 
                      : "USER";
        
        User user = userMapper.userRequestToUser(userRequest, hashedPassword, role);
    
        User savedUser = userRepository.save(user);
        
        return userMapper.userToUserResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Usuario", "ID", id) 
        );
        return userMapper.userToUserResponse(user);
    }
    
    @Override
    public UserResponse getUserByName(String name) {
        User user = userRepository.findByName(name).orElseThrow(() ->
            new ResourceNotFoundException("Usuario", "nombre", name)
        );
        return userMapper.userToUserResponse(user);
    }

    @Override
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
            .map(userMapper::userToUserResponse)
            .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(UUID id, UserRequest userRequest) {
        
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", "ID", id));
        
        if (!existingUser.getEmail().equals(userRequest.getEmail())) {
            if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
                throw new DuplicateResourceException(
                    "El email '" + userRequest.getEmail() + "' ya está registrado por otro usuario."
                );
            }
        }
        
        existingUser.setName(userRequest.getName());
        existingUser.setEmail(userRequest.getEmail());
        
        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(userRequest.getPassword());
            existingUser.setPassword(hashedPassword);
        }
        
        if (userRequest.getRole() != null && !userRequest.getRole().isEmpty()) {
            existingUser.setRole(userRequest.getRole());
        }
        
        User updatedUser = userRepository.save(existingUser);
        
        return userMapper.userToUserResponse(updatedUser);
    }

    @Override
    public void deleteUser(UUID id) {
        
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario", "ID", id));
        
        userRepository.delete(user);
        
    }
}