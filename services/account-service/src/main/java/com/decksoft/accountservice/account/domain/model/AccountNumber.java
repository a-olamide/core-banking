package com.decksoft.accountservice.account.domain.model;

import java.util.Objects;

public record AccountNumber(String value) {

    public AccountNumber {
        Objects.requireNonNull(value, "value");
        if (!value.matches("\\d{10}")) {
            throw new IllegalArgumentException("AccountNumber must be 10 digits");
        }
    }

    public String bankCode()     { return value.substring(0, 3); }
    public String productCode()  { return value.substring(3, 6); }
    public String sequence()     { return value.substring(6); }
}
