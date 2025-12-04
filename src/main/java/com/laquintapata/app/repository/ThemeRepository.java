package com.laquintapata.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laquintapata.app.entity.Theme;

public interface ThemeRepository extends JpaRepository <Theme, Long>{
    
}
