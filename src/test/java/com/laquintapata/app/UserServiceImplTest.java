package com.laquintapata.app;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.laquintapata.app.dto.request.UserRequest;
import com.laquintapata.app.dto.response.UserResponse;
import com.laquintapata.app.entity.User;
import com.laquintapata.app.exception.DuplicateResourceException;
import com.laquintapata.app.exception.ResourceNotFoundException;
import com.laquintapata.app.mapper.UserMapper;
import com.laquintapata.app.repository.UserRepository;
import com.laquintapata.app.service.impl.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequest request;
    private User user;
    private UserResponse response;

    @BeforeEach
    void setUp() {
        request = new UserRequest();
        request.setName("Test User");
        request.setEmail("test@example.com");
        request.setPassword("password123");
        request.setRole("ADMIN");

        user = User.builder()
                .id(UUID.randomUUID())
                .name("Test User")
                .email("test@example.com")
                .role("ADMIN")
                .password("hashedPassword")
                .build();

        response = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Test
    void createAdmin_success() {
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("hashedPassword");
        when(userMapper.userRequestToUser(eq(request), eq("hashedPassword"), eq("ADMIN"))).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserResponse(user)).thenReturn(response);

        UserResponse result = userService.createUser(request);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(request.getEmail());
        verify(userRepository).findByEmail(request.getEmail());
        verify(passwordEncoder).encode(request.getPassword());
        verify(userRepository).save(user);
    }

    @Test
    void createAdmin_duplicateEmail_throws() {
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));

        assertThatThrownBy(() -> userService.createUser(request))
            .isInstanceOf(DuplicateResourceException.class)
            .hasMessageContaining(request.getEmail());

        verify(userRepository).findByEmail(request.getEmail());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any());
    }

    @Test
    void getUserById_success() {
        UUID id = user.getId();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.userToUserResponse(user)).thenReturn(response);

        UserResponse r = userService.getUserById(id);

        assertThat(r).isNotNull();
        assertThat(r.getId()).isEqualTo(id);
        verify(userRepository).findById(id);
    }

    @Test
    void getUserById_notFound_throws() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(id))
            .isInstanceOf(ResourceNotFoundException.class);

        verify(userRepository).findById(id);
    }

    @Test
    void loadUserByUsername_success() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        UserDetails ud = userService.loadUserByUsername(user.getEmail());

        assertThat(ud).isNotNull();
        assertThat(ud.getUsername()).isEqualTo(user.getEmail());
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    void loadUserByUsername_notFound_throws() {
        when(userRepository.findByEmail("noone@example.com")).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(
            org.springframework.security.core.userdetails.UsernameNotFoundException.class,
            () -> userService.loadUserByUsername("noone@example.com")
        );

        verify(userRepository).findByEmail("noone@example.com");
    }

    @Test
    void getUsers_success() {
        List<User> list = List.of(user);
        when(userRepository.findAll()).thenReturn(list);
        when(userMapper.userToUserResponse(user)).thenReturn(response);

        List<UserResponse> out = userService.getUsers();

        assertThat(out).hasSize(1);
        assertThat(out.get(0).getEmail()).isEqualTo(user.getEmail());
        verify(userRepository).findAll();
    }

    @Test
    void getUserByName_success() {
        when(userRepository.findByName(user.getName())).thenReturn(Optional.of(user));
        when(userMapper.userToUserResponse(user)).thenReturn(response);

        UserResponse out = userService.getUserByName(user.getName());

        assertThat(out.getName()).isEqualTo(user.getName());
        verify(userRepository).findByName(user.getName());
    }

    @Test
    void getUserByName_notFound_throws() {
        when(userRepository.findByName("noName")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserByName("noName"))
            .isInstanceOf(ResourceNotFoundException.class);

        verify(userRepository).findByName("noName");
    }
}
