package com.laquintapata.app.entity;

import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "migrants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Migrant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "migrant")
    private List<Video> videos;

    @ManyToOne
    @JoinColumn(name = "origin_id", nullable = false)
    private Origin origin;

}