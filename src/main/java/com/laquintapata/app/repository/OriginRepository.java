package com.laquintapata.app.repository;

import com.laquintapata.app.entity.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Integer> {

    Optional<Origin> findByCountry(String country);
    
    boolean existsByCountry(String country);
}