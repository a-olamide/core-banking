package com.decksoft.ledgerservice.ledger.domian.journal;

import java.util.Objects;
import java.util.UUID;

public final class JournalEntryId {

    private final UUID value;

    private JournalEntryId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static JournalEntryId newId() {
        return new JournalEntryId(UUID.randomUUID());
    }

    public static JournalEntryId from(UUID value) {
        return new JournalEntryId(value);
    }

    public UUID value() {
        return value;
    }
}
