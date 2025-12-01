package com.laquintapata.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laquintapata.app.dto.request.UserRequest;
import com.laquintapata.app.dto.response.UserResponse;
import com.laquintapata.app.service.interfaces.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)

class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @Test
    void createAdmin_returnsCreated() throws Exception {
        UserRequest req = new UserRequest();
        req.setName("Test");
        req.setEmail("test@example.com");
        req.setPassword("password123");

        UserResponse resp = UserResponse.builder()
                .id(UUID.randomUUID())
                .name("Test")
                .email("test@example.com")
                .role("ADMIN")
                .build();

        when(userService.createAdmin(any(UserRequest.class))).thenReturn(resp);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    void getUsers_returnsList() throws Exception {
        UserResponse resp = UserResponse.builder()
                .id(UUID.randomUUID())
                .name("TestUser")
                .email("test@example.com")
                .role("ADMIN")
                .build();

        when(userService.getUsers()).thenReturn(List.of(resp));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("test@example.com"))
                .andExpect(jsonPath("$[0].name").value("TestUser"));
    }

    @Test
    void getUserById_returnsUser() throws Exception {
        UUID id = UUID.randomUUID();

        UserResponse resp = UserResponse.builder()
                .id(id)
                .name("Test")
                .email("test@example.com")
                .role("ADMIN")
                .build();

        when(userService.getUserById(id)).thenReturn(resp);

        mockMvc.perform(get("/api/users/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void getUserByName_returnsUser() throws Exception {
        UserResponse resp = UserResponse.builder()
                .id(UUID.randomUUID())
                .name("TestName")
                .email("test@example.com")
                .role("ADMIN")
                .build();

        when(userService.getUserByName("TestName")).thenReturn(resp);

        mockMvc.perform(get("/api/users/name/{name}", "TestName"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TestName"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }
}
