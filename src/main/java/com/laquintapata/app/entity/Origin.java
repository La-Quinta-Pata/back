package com.laquintapata.app.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "origins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Origin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String country;

    @OneToMany(mappedBy = "origin")
    private List<Migrant> migrant;
}