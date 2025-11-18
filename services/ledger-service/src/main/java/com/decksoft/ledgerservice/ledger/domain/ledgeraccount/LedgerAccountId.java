package com.decksoft.ledgerservice.ledger.domian.ledgeraccount;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public final class LedgerAccountId {
    private final UUID value;

    private LedgerAccountId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static LedgerAccountId newId() {
        return new LedgerAccountId(UUID.randomUUID());
    }

    public static LedgerAccountId from(UUID value) {
        return new LedgerAccountId(value);
    }

    public UUID value() {
        return value;
    }
}