package com.laquintapata.app.service.impl;

import com.laquintapata.app.dto.request.OriginRequest;
import com.laquintapata.app.dto.response.OriginResponse;
import com.laquintapata.app.entity.Origin;
import com.laquintapata.app.exception.DuplicateResourceException;
import com.laquintapata.app.exception.ResourceNotFoundException;
import com.laquintapata.app.mapper.OriginMapper;
import com.laquintapata.app.repository.OriginRepository;
import com.laquintapata.app.service.interfaces.OriginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OriginServiceImpl implements OriginService {

    private final OriginRepository originRepository;
    private final OriginMapper originMapper;

    @Override
    public OriginResponse createOrigin(OriginRequest request) {
        
        if (originRepository.existsByCountry(request.getCountry())) {
            throw new DuplicateResourceException(
                "Ya existe un país con el nombre '" + request.getCountry() + "'"
            );
        }
        
        Origin origin = originMapper.originRequestToOrigin(request);
        
        Origin savedOrigin = originRepository.save(origin);
        
        return originMapper.originToOriginResponse(savedOrigin);
    }

    @Override
    public OriginResponse getOriginById(Integer id) {
        Origin origin = originRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("País", "ID", id));
        
        return originMapper.originToOriginResponse(origin);
    }

    @Override
    public OriginResponse getOriginByCountry(String country) {
        Origin origin = originRepository.findByCountry(country)
            .orElseThrow(() -> new ResourceNotFoundException("País", "nombre", country));
        
        return originMapper.originToOriginResponse(origin);
    }

    @Override
    public List<OriginResponse> getAllOrigins() {
        return originRepository.findAll().stream()
            .map(originMapper::originToOriginResponse)
            .collect(Collectors.toList());
    }

    @Override
    public OriginResponse updateOrigin(Integer id, OriginRequest request) {
        
        Origin existingOrigin = originRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("País", "ID", id));
        
        if (!existingOrigin.getCountry().equals(request.getCountry())) {
            if (originRepository.existsByCountry(request.getCountry())) {
                throw new DuplicateResourceException(
                    "Ya existe otro país con el nombre '" + request.getCountry() + "'"
                );
            }
        }
        
        existingOrigin.setCountry(request.getCountry());
        
        Origin updatedOrigin = originRepository.save(existingOrigin);
        
        return originMapper.originToOriginResponse(updatedOrigin);
    }

    @Override
    public void deleteOrigin(Integer id) {
        
        Origin origin = originRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("País", "ID", id));
        
        originRepository.delete(origin);
    }
}