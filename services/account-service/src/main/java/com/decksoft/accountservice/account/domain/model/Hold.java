package com.decksoft.accountservice.account.domain.model;

import common.domain.Money;

import java.time.Instant;
import java.util.UUID;

public class Hold {

    private final UUID holdId;
    private final Money amount;
    private final String reason;
    private final Instant createdAt;
    private final Instant expiresAt;

    public Hold(UUID holdId, Money amount, String reason, Instant createdAt, Instant expiresAt) {
        this.holdId = holdId;
        this.amount = amount;
        this.reason = reason;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public static Hold create(Money amount, String reason, Instant expiresAt) {
        return new Hold(UUID.randomUUID(), amount, reason, Instant.now(), expiresAt);
    }

    public UUID getHoldId() { return holdId; }
    public Money getAmount() { return amount; }
}
