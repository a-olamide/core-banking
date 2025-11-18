package com.decksoft.ledgerservice.ledger.domian.journal;


import com.decksoft.ledgerservice.ledger.domian.ledgeraccount.LedgerAccountId;
import common.domain.Money;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public final class JournalEntryLine {

    private final int lineNumber;
    private final LedgerAccountId ledgerAccountId;
    private final UUID accountId;        // from account-service (optional)
    private final String accountNumber;  // denormalized
    private final EntryDirection direction;
    private final Money amount;

    public JournalEntryLine(
            int lineNumber,
            LedgerAccountId ledgerAccountId,
            UUID accountId,
            String accountNumber,
            EntryDirection direction,
            Money amount
    ) {
        this.lineNumber = lineNumber;
        this.ledgerAccountId = Objects.requireNonNull(ledgerAccountId);
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.direction = Objects.requireNonNull(direction);
        this.amount = Objects.requireNonNull(amount);
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public LedgerAccountId getLedgerAccountId() {
        return ledgerAccountId;
    }

    public Optional<UUID> getAccountId() {
        return Optional.ofNullable(accountId);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public EntryDirection getDirection() {
        return direction;
    }

    public Money getAmount() {
        return amount;
    }
}
