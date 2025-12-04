package com.laquintapata.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.laquintapata.app.dto.request.ThemeRequestDTO;
import com.laquintapata.app.dto.response.ThemeResponseDTO;
import com.laquintapata.app.entity.Theme;
import com.laquintapata.app.mapper.ThemeMapper;
import com.laquintapata.app.repository.ThemeRepository;
import com.laquintapata.app.service.interfaces.ThemeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    @Override
    public ThemeResponseDTO create(ThemeRequestDTO dto) {
        Theme theme = themeMapper.toEntity(dto);
        return themeMapper.toResponseDTO(themeRepository.save(theme));

    }

    @Override
    public ThemeResponseDTO update(Long id, ThemeRequestDTO dto) {
        Theme existing = themeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tema no encontrado con id: " + id));

        themeMapper.updateEntityFromRequest(dto, existing);

        Theme updated = themeRepository.save(existing);
        return themeMapper.toResponseDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (!themeRepository.existsById(id)) {
            throw new RuntimeException("Tema no encontrado con id: " + id);
        }

        themeRepository.deleteById(id);
    }

    @Override
    public ThemeResponseDTO getById(Long id) {
        return themeRepository.findById(id)
                .map(themeMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Tema no encontrado con id: " + id));
    }

    @Override
    public List<ThemeResponseDTO> getAll() {
        return themeRepository.findAll()
                .stream()
                .map(themeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
