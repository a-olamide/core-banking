package com.decksoft.customerservice.customer.domain.model.value;

import java.util.Objects;

public final class PhoneNumber {

    private final String countryCode;   // e.g. "+234"
    private final String nationalNumber;

    public PhoneNumber(String countryCode, String nationalNumber) {
        this.countryCode = validateNotBlank(countryCode, "countryCode");
        this.nationalNumber = validateNotBlank(nationalNumber, "nationalNumber");
    }

    private String validateNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value.trim();
    }

    public String countryCode() { return countryCode; }
    public String nationalNumber() { return nationalNumber; }

    public String asE164() {
        return countryCode + nationalNumber;
    }

    @Override
    public String toString() {
        return asE164();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber that)) return false;
        return Objects.equals(countryCode, that.countryCode)
                && Objects.equals(nationalNumber, that.nationalNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, nationalNumber);
    }
}
