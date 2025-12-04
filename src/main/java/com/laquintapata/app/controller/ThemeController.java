package com.laquintapata.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laquintapata.app.dto.request.ThemeRequestDTO;
import com.laquintapata.app.dto.response.ThemeResponseDTO;
import com.laquintapata.app.service.interfaces.ThemeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {
    
    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @PostMapping
    public ResponseEntity<ThemeResponseDTO> create(@Valid @RequestBody ThemeRequestDTO dto) {
        return new ResponseEntity<>(themeService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThemeResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ThemeRequestDTO dto
    ) {
        return ResponseEntity.ok(themeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        themeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThemeResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(themeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ThemeResponseDTO>> getAll() {
        return ResponseEntity.ok(themeService.getAll());
    }
    
}
