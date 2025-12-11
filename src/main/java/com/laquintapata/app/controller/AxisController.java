package com.laquintapata.app.controller;

import com.laquintapata.app.entity.Axis;
import com.laquintapata.app.repository.AxisRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/axes")
@RequiredArgsConstructor

public class AxisController {

    private final AxisRepository axisRepository;

    @GetMapping
    public ResponseEntity<List<Axis>> getAllAxes() {
        return ResponseEntity.ok(axisRepository.findAll());
    }
}

