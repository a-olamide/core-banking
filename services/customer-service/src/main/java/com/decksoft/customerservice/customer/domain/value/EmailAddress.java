package com.decksoft.customerservice.customer.domain.model.value;

import java.util.Objects;
import java.util.regex.Pattern;

public final class EmailAddress {

    private static final Pattern SIMPLE_PATTERN =
            Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private final String value;

    public EmailAddress(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("email must not be blank");
        }
        String trimmed = value.trim();
        if (!SIMPLE_PATTERN.matcher(trimmed).matches()) {
            throw new IllegalArgumentException("invalid email format: " + value);
        }
        this.value = trimmed.toLowerCase(); // normalize
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailAddress that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
