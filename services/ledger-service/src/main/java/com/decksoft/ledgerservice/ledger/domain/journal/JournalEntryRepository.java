package com.decksoft.ledgerservice.ledger.domian.journal;

import java.util.Optional;

public interface JournalEntryRepository {

    JournalEntry save(JournalEntry entry);

    Optional<JournalEntry> findById(JournalEntryId id);
}