package com.decksoft.accountservice.api.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateAccountRequest(
        @NotBlank String customerId,
        @NotBlank String accountNumber,
        @NotBlank String type,
        @NotBlank String currency,
        @NotNull BigDecimal openingBalance
) {}
