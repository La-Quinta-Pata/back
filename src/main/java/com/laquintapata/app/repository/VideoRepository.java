package com.laquintapata.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laquintapata.app.entity.Video;

public interface VideoRepository extends JpaRepository <Video, Long> {
    
}
