package com.laquintapata.app.dto.response;

  import lombok.AllArgsConstructor;
  import lombok.Builder;
  import lombok.Data;
  import lombok.NoArgsConstructor;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public class AuthResponseDTO {

      private UserResponse user;

      @com.fasterxml.jackson.annotation.JsonIgnore
      private String token;
  }
