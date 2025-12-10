package com.laquintapata.app.controller;

import com.laquintapata.app.dto.request.AxisRequestDTO;
import com.laquintapata.app.dto.response.AxisResponseDTO;
import com.laquintapata.app.service.interfaces.AxisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/axes")
@RequiredArgsConstructor
public class AxisController {

    private final AxisService axisService;

    @GetMapping
    public ResponseEntity<List<AxisResponseDTO>> getAll() {
        List<AxisResponseDTO> axes = axisService.getAll();
        return ResponseEntity.ok(axes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AxisResponseDTO> getById(@PathVariable Long id) {
        AxisResponseDTO response = axisService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AxisResponseDTO> create(@Valid @RequestBody AxisRequestDTO dto) {
        AxisResponseDTO response = axisService.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        axisService.delete(id);
        return ResponseEntity.noContent().build();
    }
}