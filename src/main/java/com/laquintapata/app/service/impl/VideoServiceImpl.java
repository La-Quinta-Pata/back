package com.laquintapata.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.laquintapata.app.dto.request.VideoRequestDTO;
import com.laquintapata.app.dto.response.VideoResponseDTO;
import com.laquintapata.app.entity.Axis;
import com.laquintapata.app.entity.Video;
import com.laquintapata.app.mapper.VideoMapper;
import com.laquintapata.app.repository.AxisRepository;
import com.laquintapata.app.repository.VideoRepository;
import com.laquintapata.app.service.interfaces.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

    private VideoRepository videoRepository;
    private VideoMapper videoMapper;
    private AxisRepository axisRepository;

    @Override
    public VideoResponseDTO create(VideoRequestDTO dto) {
        Video video = videoMapper.toEntity(dto);

     
        Axis axis = axisRepository.findById(dto.getAxisId())
                .orElseThrow(() -> new RuntimeException("Axis no encontrado"));
        
                video.setAxis(axis);
        Video saved = videoRepository.save(video);
        return videoMapper.toResponseDTO(saved);
    }

    @Override
    public VideoResponseDTO update(Long id, VideoRequestDTO dto) {
        Video existing = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video no encontrado con id: " + id));

        videoMapper.updateEntityFromRequest(dto, existing);

        if (dto.getAxisId() != null) {
            Axis axis = axisRepository.findById(dto.getAxisId())
                    .orElseThrow(() -> new RuntimeException("Axis no encontrado"));
            existing.setAxis(axis);
        }
        
        Video updated = videoRepository.save(existing);
        return videoMapper.toResponseDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (!videoRepository.existsById(id)) {
            throw new RuntimeException("Video no encontrado con id: " + id);
        }
        videoRepository.deleteById(id);
    }

    @Override
    public VideoResponseDTO getById(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video no encontrado con id: " + id));

        return videoMapper.toResponseDTO(video);
    }

    @Override
    public List<VideoResponseDTO> getAll() {
        return videoRepository.findAll()
                .stream()
                .map(videoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
