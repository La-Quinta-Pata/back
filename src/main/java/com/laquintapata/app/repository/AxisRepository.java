package com.laquintapata.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laquintapata.app.entity.Axis;

public interface AxisRepository extends JpaRepository <Axis, Long> {
    
}
