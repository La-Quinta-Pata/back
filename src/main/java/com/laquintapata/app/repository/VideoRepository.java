package com.laquintapata.app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laquintapata.app.entity.Video;

public interface VideoRepository extends JpaRepository <Video, Long> {

    List<Video> findByUserId(UUID userId);
    
}
