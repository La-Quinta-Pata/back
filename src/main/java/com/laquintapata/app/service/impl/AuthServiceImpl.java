package com.laquintapata.app.service.impl;

  import com.laquintapata.app.dto.request.AuthRequest;
  import com.laquintapata.app.dto.response.AuthResponseDTO;
  import com.laquintapata.app.dto.response.UserResponse;
  import com.laquintapata.app.entity.User;
  import com.laquintapata.app.mapper.UserMapper;
  import com.laquintapata.app.repository.UserRepository;
  import com.laquintapata.app.security.JwtTokenProvider;
  import com.laquintapata.app.service.interfaces.AuthService;
  import lombok.RequiredArgsConstructor;
  import lombok.extern.slf4j.Slf4j;
  import org.springframework.security.authentication.AuthenticationManager;
  import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
  import org.springframework.security.core.Authentication;
  import org.springframework.security.core.context.SecurityContextHolder;
  import org.springframework.stereotype.Service;

  @Service
  @RequiredArgsConstructor
  @Slf4j
  public class AuthServiceImpl implements AuthService {

      private final AuthenticationManager authenticationManager;

      private final UserRepository userRepository;

      private final JwtTokenProvider jwtTokenProvider;

      private final UserMapper userMapper;

      @Override
      public AuthResponseDTO login(AuthRequest request) {
          log.info("Authenticating user with email: {}", request.getEmail());

          Authentication authentication = authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                          request.getEmail(),
                          request.getPassword()));

          SecurityContextHolder.getContext().setAuthentication(authentication);

          User user = userRepository.findByEmail(request.getEmail())
                  .orElseThrow(() -> new RuntimeException("User not found"));

          String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail());

          log.info("User authenticated successfully: {}", request.getEmail());

          UserResponse userResponse = userMapper.userToUserResponse(user);

          return AuthResponseDTO.builder()
                  .token(token)
                  .tokenType("Bearer")
                  .expiresIn(86400000L)
                  .user(userResponse)
                  .build();
      }
  }
