package com.laquintapata.app.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.laquintapata.app.entity.User;
import com.laquintapata.app.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@laquintapata.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@laquintapata.com");
                admin.setPassword(passwordEncoder.encode("Admin123!"));
                admin.setName("Administrador");
                admin.setRole("ADMIN");
                
                userRepository.save(admin);
                System.out.println("Usuario admin creado exitosamente!");
                System.out.println("Email: admin@laquintapata.com");
                System.out.println("Password: Admin123!");
            } else {
                System.out.println("Usuario admin ya existe en la base de datos");
            }
        };
    }
}