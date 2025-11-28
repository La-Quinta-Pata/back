package com.laquintapata.app.service.interfaces;

import java.util.List;

import com.laquintapata.app.dto.request.AxisRequestDTO;
import com.laquintapata.app.dto.response.AxisResponseDTO;

public interface AxisService {
    AxisResponseDTO create(AxisRequestDTO dto);

    void delete(Long id);

    AxisResponseDTO getById(Long id);

    List<AxisResponseDTO> getAll();
}
