package com.laquintapata.app.service.impl;

import com.laquintapata.app.dto.request.MigrantRequest;
import com.laquintapata.app.dto.response.MigrantResponse;
import com.laquintapata.app.entity.Migrant;
import com.laquintapata.app.exception.DuplicateResourceException;
import com.laquintapata.app.exception.ResourceNotFoundException;
import com.laquintapata.app.mapper.MigrantMapper;
import com.laquintapata.app.repository.MigrantRepository;
import com.laquintapata.app.service.interfaces.MigrantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MigrantServiceImpl implements MigrantService {

    private final MigrantRepository migrantRepository;
    private final MigrantMapper migrantMapper;

    @Override
    public MigrantResponse createMigrant(MigrantRequest request) {
        
        if (migrantRepository.existsByNameAndLastName(request.getName(), request.getLastName())) {
            throw new DuplicateResourceException(
                "Ya existe un migrante con el nombre '" + request.getName() + 
                "' y apellido '" + request.getLastName() + "'"
            );
        }
        
        Migrant migrant = migrantMapper.migrantRequestToMigrant(request);
        
        Migrant savedMigrant = migrantRepository.save(migrant);
        
        return migrantMapper.migrantToMigrantResponse(savedMigrant);
    }

    @Override
    public MigrantResponse getMigrantById(UUID id) {
        Migrant migrant = migrantRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Migrante", "ID", id));
        
        return migrantMapper.migrantToMigrantResponse(migrant);
    }

    @Override
    public MigrantResponse getMigrantByName(String name) {
        Migrant migrant = migrantRepository.findByName(name)
            .orElseThrow(() -> new ResourceNotFoundException("Migrante", "nombre", name));
        
        return migrantMapper.migrantToMigrantResponse(migrant);
    }

    @Override
    public List<MigrantResponse> getAllMigrants() {
        return migrantRepository.findAll().stream()
            .map(migrantMapper::migrantToMigrantResponse)
            .collect(Collectors.toList());
    }

    @Override
    public MigrantResponse updateMigrant(UUID id, MigrantRequest request) {
        
        Migrant existingMigrant = migrantRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Migrante", "ID", id));
        
        if (!existingMigrant.getName().equals(request.getName()) || 
            !existingMigrant.getLastName().equals(request.getLastName())) {
            
            if (migrantRepository.existsByNameAndLastName(request.getName(), request.getLastName())) {
                throw new DuplicateResourceException(
                    "Ya existe otro migrante con el nombre '" + request.getName() + 
                    "' y apellido '" + request.getLastName() + "'"
                );
            }
        }
        
        existingMigrant.setName(request.getName());
        existingMigrant.setLastName(request.getLastName());
        existingMigrant.setOriginId(request.getOriginId());
        
   
        Migrant updatedMigrant = migrantRepository.save(existingMigrant);
        
        return migrantMapper.migrantToMigrantResponse(updatedMigrant);
    }

    @Override
    public void deleteMigrant(UUID id) {
        
        Migrant migrant = migrantRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Migrante", "ID", id));
        
        migrantRepository.delete(migrant);
    }
}