package com.laquintapata.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "users")  
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false, unique = true)
    private String email;

    @Column()
    private String name;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    @ToString.Exclude
    private String password;
}