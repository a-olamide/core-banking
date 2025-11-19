package com.decksoft.apigatewayservice.security.dto;


public record LoginResponse(
        String accessToken,
        String tokenType,
        long expiresInSeconds
) {}
