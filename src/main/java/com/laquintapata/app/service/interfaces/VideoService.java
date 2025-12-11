package com.laquintapata.app.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.laquintapata.app.dto.request.VideoRequestDTO;
import com.laquintapata.app.dto.response.CatalogResponseDTO;
import com.laquintapata.app.dto.response.VideoResponseDTO;


@Service
public interface VideoService {
    VideoResponseDTO create(VideoRequestDTO dto);

    VideoResponseDTO update(Long id, VideoRequestDTO dto);

    void delete(Long id);

    VideoResponseDTO getById(Long id);

    List<VideoResponseDTO> getAll();
    
    List<CatalogResponseDTO> getCatalog();
    List<CatalogResponseDTO> getByUser(UUID userId);

}
