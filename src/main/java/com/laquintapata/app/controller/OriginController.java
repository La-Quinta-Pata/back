package com.laquintapata.app.controller;

import com.laquintapata.app.dto.request.OriginRequest;
import com.laquintapata.app.dto.response.OriginResponse;
import com.laquintapata.app.service.interfaces.OriginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/origins")
@RequiredArgsConstructor
public class OriginController {

    private final OriginService originService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OriginResponse> createOrigin(@Valid @RequestBody OriginRequest request) {
        OriginResponse response = originService.createOrigin(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OriginResponse>> getAllOrigins() {
        List<OriginResponse> origins = originService.getAllOrigins();
        return new ResponseEntity<>(origins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OriginResponse> getOriginById(@PathVariable Integer id) {
        OriginResponse response = originService.getOriginById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/country/{country}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OriginResponse> getOriginByCountry(@PathVariable String country) {
        OriginResponse response = originService.getOriginByCountry(country);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OriginResponse> updateOrigin(
            @PathVariable Integer id,
            @Valid @RequestBody OriginRequest request) {
        OriginResponse response = originService.updateOrigin(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteOrigin(@PathVariable Integer id) {
        originService.deleteOrigin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}