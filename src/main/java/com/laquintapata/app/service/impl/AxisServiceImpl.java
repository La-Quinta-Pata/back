package com.laquintapata.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.laquintapata.app.dto.request.AxisRequestDTO;
import com.laquintapata.app.dto.response.AxisResponseDTO;
import com.laquintapata.app.entity.Axis;
import com.laquintapata.app.mapper.AxisMapper;
import com.laquintapata.app.repository.AxisRepository;
import com.laquintapata.app.service.interfaces.AxisService;

@Service
public class AxisServiceImpl implements AxisService {

    private AxisRepository axisRepository;
    private AxisMapper axisMapper;

    @Override
    public AxisResponseDTO create(AxisRequestDTO dto) {
        Axis axis = axisMapper.toEntity(dto);
        Axis saved = axisRepository.save(axis);
        return axisMapper.toResponseDTO(saved);
    }

    @Override
    public void delete(Long id) {
        if (!axisRepository.existsById(id)) {
            throw new RuntimeException("Axis no encontrado: " + id);
        }
        axisRepository.deleteById(id);
    }

    @Override
    public AxisResponseDTO getById(Long id) {
        return axisRepository.findById(id)
                .map(axisMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Axis no encontrado: " + id));
    }

    @Override
    public List<AxisResponseDTO> getAll() {
        return axisRepository.findAll()
                .stream()
                .map(axisMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
