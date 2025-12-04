package com.laquintapata.app.service.interfaces;

import com.laquintapata.app.dto.request.OriginRequest;
import com.laquintapata.app.dto.response.OriginResponse;
import org.springframework.lang.NonNull;

import java.util.List;

public interface OriginService {

    OriginResponse createOrigin(@NonNull OriginRequest request);

    OriginResponse getOriginById(@NonNull Integer id);

    OriginResponse getOriginByCountry(String country);

    List<OriginResponse> getAllOrigins();

    OriginResponse updateOrigin(@NonNull Integer id, @NonNull OriginRequest request);

    void deleteOrigin(@NonNull Integer id);
}