package com.laquintapata.app.service.interfaces;

import java.util.List;

import com.laquintapata.app.dto.request.ThemeRequestDTO;
import com.laquintapata.app.dto.response.ThemeResponseDTO;

public interface ThemeService {

    ThemeResponseDTO create(ThemeRequestDTO dto);

    ThemeResponseDTO update(Long id, ThemeRequestDTO dto);

    void delete(Long id);

    ThemeResponseDTO getById(Long id);

    List<ThemeResponseDTO> getAll();
    
}
