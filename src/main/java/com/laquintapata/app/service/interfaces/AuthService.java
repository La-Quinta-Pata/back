package com.laquintapata.app.service.interfaces;

import com.laquintapata.app.dto.request.AuthRequest;
import com.laquintapata.app.dto.response.AuthResponseDTO;

public interface AuthService {

    AuthResponseDTO login(AuthRequest request);
}
