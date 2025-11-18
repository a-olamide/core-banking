package com.decksoft.accountservice.api.dto;


import java.math.BigDecimal;

public record AccountResponse(
        String accountId,
        String customerId,
        String accountNumber,
        String type,
        String status,
        BigDecimal ledgerBalance,
        BigDecimal availableBalance,
        String currency
) {}
