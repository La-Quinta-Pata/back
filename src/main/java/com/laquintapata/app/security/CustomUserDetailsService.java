package com.laquintapata.app.security;

  import com.laquintapata.app.entity.User;
  import com.laquintapata.app.repository.UserRepository;
  import lombok.RequiredArgsConstructor;
  import lombok.extern.slf4j.Slf4j;
  import org.springframework.security.core.userdetails.UserDetails;
  import org.springframework.security.core.userdetails.UserDetailsService;
  import org.springframework.security.core.userdetails.UsernameNotFoundException;
  import org.springframework.stereotype.Service;

  @Service
  @RequiredArgsConstructor
  @Slf4j
  public class CustomUserDetailsService implements UserDetailsService {

      private final UserRepository userRepository;

      @Override
      public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          User user = userRepository.findByEmail(email)
                  .orElseThrow(() -> {
                      log.error("Usuario no encontrado con email {}", email);
                      return new UsernameNotFoundException("Usuario no encontrado con email: " + email);
                  });

          log.debug("User cargado con éxito: {}", email);
          return new UserDetail(user);
      }
  }
