package com.laquintapata.app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.laquintapata.app.dto.request.VideoRequestDTO;
import com.laquintapata.app.dto.response.CatalogResponseDTO;
import com.laquintapata.app.dto.response.VideoResponseDTO;
import com.laquintapata.app.service.interfaces.VideoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor

public class VideoController {
    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<List<VideoResponseDTO>> getAll() {
        List<VideoResponseDTO> response = videoService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/catalog")
    public ResponseEntity<List<CatalogResponseDTO>> getCatalog() {
        return ResponseEntity.ok(videoService.getCatalog());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoResponseDTO> getById(@PathVariable Long id) {
        VideoResponseDTO response = videoService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<VideoResponseDTO> create(@Valid @RequestBody VideoRequestDTO dto) {
        VideoResponseDTO response = videoService.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody VideoRequestDTO dto) {
        VideoResponseDTO response = videoService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        videoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<VideoResponseDTO>> getByUser(@PathVariable UUID userId) {
        List<VideoResponseDTO> videos = videoService.getByUser(userId);
        return ResponseEntity.ok(videos);
    }
}
