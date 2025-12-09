package com.laquintapata.app.service.interfaces;

import com.laquintapata.app.dto.request.MigrantRequest;
import com.laquintapata.app.dto.response.MigrantResponse;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public interface MigrantService {

    MigrantResponse createMigrant(@NonNull MigrantRequest request);

    MigrantResponse getMigrantById(@NonNull UUID id);

    MigrantResponse getMigrantByName(String name);

    List<MigrantResponse> getAllMigrants();

    MigrantResponse updateMigrant(@NonNull UUID id, @NonNull MigrantRequest request);

    void deleteMigrant(@NonNull UUID id);
}