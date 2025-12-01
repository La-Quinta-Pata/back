package com.laquintapata.app.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.laquintapata.app.config.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(UUID userId, String email) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());

            return JWT.create()
                    .withSubject(userId.toString())
                    .withClaim("email", email)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMs()))
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("Error generating JWT token", e);
            throw new RuntimeException("Error generating JWT token", e);
        }
    }

    public UUID getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());

            String userId = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();

            return UUID.fromString(userId);
        } catch (JWTVerificationException e) {
            log.error("Error extracting userId from token", e);
            return null;
        }
    }

    public String getEmailFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());

            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getClaim("email")
                    .asString();
        } catch (JWTVerificationException e) {
            log.error("Error extracting email from token", e);
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());

            JWT.require(algorithm)
                    .build()
                    .verify(token);

            return true;
        } catch (JWTVerificationException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }
}
