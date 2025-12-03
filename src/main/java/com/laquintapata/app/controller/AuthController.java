  package com.laquintapata.app.controller;

  import com.laquintapata.app.dto.request.AuthRequest;
  import com.laquintapata.app.dto.response.AuthResponseDTO;
  import com.laquintapata.app.service.interfaces.AuthService;
  import jakarta.validation.Valid;
  import lombok.RequiredArgsConstructor;
  import lombok.extern.slf4j.Slf4j;
  import org.springframework.http.HttpHeaders;
  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.*;

  @RestController
  @RequestMapping("/api/auth")
  @RequiredArgsConstructor
  @Slf4j
  public class AuthController {

      private final AuthService authService;

      @PostMapping("/login")
      public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequest request) {
          log.info("Usuario autenticado con el correo: {}", request.getEmail());
          AuthResponseDTO response = authService.login(request);

          HttpHeaders headers = new HttpHeaders();
          headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + response.getToken());

          return ResponseEntity.ok().headers(headers).body(response);
      }
  }
