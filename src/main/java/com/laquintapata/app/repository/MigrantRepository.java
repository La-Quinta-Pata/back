package com.laquintapata.app.repository;

import com.laquintapata.app.entity.Migrant;
import com.laquintapata.app.entity.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MigrantRepository extends JpaRepository<Migrant, UUID> {

    Optional<Migrant> findByName(String name);    
 
    boolean existsByName(String name);

    Optional<Migrant> findByNameAndOrigin(String name, Origin origin);

}