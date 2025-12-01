package com.laquintapata.app.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.laquintapata.app.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    
}

