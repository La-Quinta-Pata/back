package com.laquintapata.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.laquintapata.app.entity.User;
import com.laquintapata.app.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Value("${app.admin.email:admin@laquintapata.com}")
    private String adminEmail;

    @Value("${app.admin.password:Admin123!}")
    private String adminPassword;

    @Value("${app.admin.name:Administrador}")
    private String adminName;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                User admin = new User();
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setName(adminName);
                admin.setRole("ADMIN");
                
                userRepository.save(admin);
                System.out.println("Usuario admin creado exitosamente!");
                System.out.println("Email: " + adminEmail);
                System.out.println("Password: [PROTECTED]");
            } else {
                System.out.println("Usuario admin ya existe en la base de datos");
            }
        };
    }
}