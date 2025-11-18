package com.decksoft.ledgerservice.ledger.domian.ledgeraccount;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Optional;

public class LedgerAccount {

    private final LedgerAccountId id;
    private final String code;
    private String name;
    private final LedgerAccountType type;
    private final String currencyCode;
    private final LedgerAccountId parentId;
    private boolean isLeaf;
    private final OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private long version;

    public LedgerAccount(
            LedgerAccountId id,
            String code,
            String name,
            LedgerAccountType type,
            String currencyCode,
            LedgerAccountId parentId,
            boolean isLeaf,
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt,
            long version
    ) {
        this.id = Objects.requireNonNull(id);
        this.code = Objects.requireNonNull(code);
        this.name = Objects.requireNonNull(name);
        this.type = Objects.requireNonNull(type);
        this.currencyCode = Objects.requireNonNull(currencyCode);
        this.parentId = parentId;
        this.isLeaf = isLeaf;
        this.createdAt = Objects.requireNonNull(createdAt);
        this.updatedAt = Objects.requireNonNull(updatedAt);
        this.version = version;
    }

    public LedgerAccountId getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public LedgerAccountType getType() {
        return type;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Optional<LedgerAccountId> getParentId() {
        return Optional.ofNullable(parentId);
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public long getVersion() {
        return version;
    }
}
