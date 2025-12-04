package com.laquintapata.app.controller;

import com.laquintapata.app.dto.request.MigrantRequest;
import com.laquintapata.app.dto.response.MigrantResponse;
import com.laquintapata.app.service.interfaces.MigrantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/migrants")
@RequiredArgsConstructor
public class MigrantController {

    private final MigrantService migrantService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MigrantResponse> createMigrant(@Valid @RequestBody MigrantRequest request) {
        MigrantResponse response = migrantService.createMigrant(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<MigrantResponse>> getAllMigrants() {
        List<MigrantResponse> migrants = migrantService.getAllMigrants();
        return new ResponseEntity<>(migrants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MigrantResponse> getMigrantById(@PathVariable UUID id) {
        MigrantResponse response = migrantService.getMigrantById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MigrantResponse> getMigrantByName(@PathVariable String name) {
        MigrantResponse response = migrantService.getMigrantByName(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MigrantResponse> updateMigrant(
            @PathVariable UUID id,
            @Valid @RequestBody MigrantRequest request) {
        MigrantResponse response = migrantService.updateMigrant(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMigrant(@PathVariable UUID id) {
        migrantService.deleteMigrant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
