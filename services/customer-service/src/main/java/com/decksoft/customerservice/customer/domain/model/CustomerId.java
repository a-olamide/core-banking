package com.decksoft.customerservice.customer.domain.customer;

import java.util.Objects;
import java.util.UUID;

public final class CustomerId {
    private final UUID value;

    private CustomerId(UUID value) {
        this.value = Objects.requireNonNull(value, "CustomerId value must not be null");
    }

    public static CustomerId from(UUID value) {
        return new CustomerId(value);
    }

    public static CustomerId newId() {
        return new CustomerId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerId that)) return false;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}