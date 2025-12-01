package com.laquintapata.app.service.interfaces;

import java.util.List;

import com.laquintapata.app.dto.request.VideoRequestDTO;
import com.laquintapata.app.dto.response.VideoResponseDTO;

public interface VideoService {
    VideoResponseDTO create(VideoRequestDTO dto);

    VideoResponseDTO update(Long id, VideoRequestDTO dto);

    void delete(Long id);

    VideoResponseDTO getById(Long id);

    List<VideoResponseDTO> getAll();

}
