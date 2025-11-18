package com.decksoft.ledgerservice.ledger.domian.journal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JournalEntry {

    private final JournalEntryId id;
    private final String externalId; // correlation with account-service / payments
    private final LocalDate bookingDate;
    private final LocalDate valueDate;
    private final String description;
    private JournalEntryStatus status;
    private final List<JournalEntryLine> lines;
    private final OffsetDateTime createdAt;
    private OffsetDateTime postedAt;
    private long version;

    public static JournalEntry createNew(
            String externalId,
            LocalDate bookingDate,
            LocalDate valueDate,
            String description,
            List<JournalEntryLine> lines,
            OffsetDateTime now
    ) {
        JournalEntry entry = new JournalEntry(
                JournalEntryId.newId(),
                externalId,
                bookingDate,
                valueDate,
                description,
                JournalEntryStatus.PENDING,
                new ArrayList<>(lines),
                now,
                null,
                0L
        );
        entry.validateDoubleEntry();
        return entry;
    }

    public JournalEntry(
            JournalEntryId id,
            String externalId,
            LocalDate bookingDate,
            LocalDate valueDate,
            String description,
            JournalEntryStatus status,
            List<JournalEntryLine> lines,
            OffsetDateTime createdAt,
            OffsetDateTime postedAt,
            long version
    ) {
        this.id = Objects.requireNonNull(id);
        this.externalId = externalId;
        this.bookingDate = Objects.requireNonNull(bookingDate);
        this.valueDate = Objects.requireNonNull(valueDate);
        this.description = description;
        this.status = Objects.requireNonNull(status);
        this.lines = new ArrayList<>(Objects.requireNonNull(lines));
        this.createdAt = Objects.requireNonNull(createdAt);
        this.postedAt = postedAt;
        this.version = version;
    }

    private void validateDoubleEntry() {
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Journal entry must have at least one line");
        }

        String currency = lines.get(0).getAmount().currency();
        BigDecimal debit = BigDecimal.ZERO;
        BigDecimal credit = BigDecimal.ZERO;

        for (JournalEntryLine line : lines) {
            if (!currency.equals(line.getAmount().currency())) {
                throw new IllegalArgumentException("All lines in a journal entry must have same currency");
            }
            if (line.getDirection() == EntryDirection.DEBIT) {
                debit = debit.add(line.getAmount().amount());
            } else {
                credit = credit.add(line.getAmount().amount());
            }
        }

        if (debit.compareTo(credit) != 0) {
            throw new IllegalArgumentException("Journal entry must be balanced: debit=" + debit + ", credit=" + credit);
        }
    }

    public void post(OffsetDateTime now) {
        if (status != JournalEntryStatus.PENDING) {
            throw new IllegalStateException("Only PENDING entries can be posted");
        }
        this.status = JournalEntryStatus.POSTED;
        this.postedAt = now;
        this.version++;
    }

    public JournalEntryId getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public String getDescription() {
        return description;
    }

    public JournalEntryStatus getStatus() {
        return status;
    }

    public List<JournalEntryLine> getLines() {
        return List.copyOf(lines);
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getPostedAt() {
        return postedAt;
    }

    public long getVersion() {
        return version;
    }
}
