package com.laquintapata.app.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "videos")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String thumbnailUrl;

    @ManyToOne
    @JoinColumn(name = "axis_id", nullable = false)
    private Axis axis;

    @ManyToOne
    @JoinColumn(name = "migrant_id", nullable = false)
    private Migrant migrant;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "video_themes", joinColumns = @JoinColumn(name = "video_id"), inverseJoinColumns = @JoinColumn(name = "theme_id"))
    private List<Theme> themes;
}
