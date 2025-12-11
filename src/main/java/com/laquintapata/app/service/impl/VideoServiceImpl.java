package com.laquintapata.app.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.laquintapata.app.dto.request.VideoRequestDTO;
import com.laquintapata.app.dto.response.CatalogResponseDTO;
import com.laquintapata.app.dto.response.VideoResponseDTO;
import com.laquintapata.app.entity.Axis;
import com.laquintapata.app.entity.Migrant;
import com.laquintapata.app.entity.User;
import com.laquintapata.app.entity.Video;
import com.laquintapata.app.mapper.VideoMapper;
import com.laquintapata.app.repository.AxisRepository;
import com.laquintapata.app.repository.MigrantRepository;
import com.laquintapata.app.repository.UserRepository;
import com.laquintapata.app.repository.VideoRepository;
import com.laquintapata.app.security.UserDetail;
import com.laquintapata.app.service.interfaces.VideoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final AxisRepository axisRepository;
    private final MigrantRepository migrantRepository;
    private final UserRepository userRepository;

    @Override
    public VideoResponseDTO create(VideoRequestDTO dto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) auth.getPrincipal();

        User user = userRepository.findByEmail(userDetail.getUsername())
                .orElseThrow(() -> new RuntimeException("User no encontrado"));

        Migrant migrant = migrantRepository.findById(dto.getMigrantId())
                .orElseThrow(() -> new RuntimeException("Migrante no encontrado"));

        Axis axis = axisRepository.findById(dto.getAxisId())
                .orElseThrow(() -> new RuntimeException("Axis no encontrado"));

        Video video = videoMapper.toEntity(dto);

        video.setMigrant(migrant);
        video.setAxis(axis);
        video.setUser(user);

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

    @Override
    public List<CatalogResponseDTO> getCatalog() {
        return videoRepository.findAll()
                .stream()
                .map(videoMapper::toCatalogDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CatalogResponseDTO> getByUser(UUID userId) {
        return videoRepository.findByUserId(userId)
                .stream()
                .map(videoMapper::toCatalogDTO)
                .toList();
    }
}
