package com.decksoft.apigatewayservice.security.dto;

public record LoginRequest(
        String username,
        String password
) {}
