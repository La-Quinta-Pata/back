package com.laquintapata.app.service.interfaces;

import com.laquintapata.app.dto.request.MigrantRequest;
import com.laquintapata.app.dto.response.MigrantResponse;

import java.util.List;
import java.util.UUID;

public interface MigrantService {

    MigrantResponse createMigrant(MigrantRequest request);

    MigrantResponse getMigrantById(UUID id);

    MigrantResponse getMigrantByName(String name);

    List<MigrantResponse> getAllMigrants();

    MigrantResponse updateMigrant(UUID id, MigrantRequest request);

    void deleteMigrant(UUID id);
}