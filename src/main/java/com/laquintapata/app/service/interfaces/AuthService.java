 package com.laquintapata.app.service.interfaces;

  import com.laquintapata.app.dto.request.UserRequest;
  import com.laquintapata.app.dto.response.AuthResponseDTO;

  public interface AuthService {

      AuthResponseDTO login(UserRequest request);
  }
