package com.laquintapata.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false, unique = true, length = 100)  
    private String email;

    @Column(length = 50)  
    private String name;


    @Column(nullable = false, length = 30) 
    private String role;

    @Column(nullable = false)
    @ToString.Exclude
    private String password;
}
