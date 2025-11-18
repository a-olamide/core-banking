package com.decksoft.customerservice.customer.domain.model.value;

import java.util.Objects;

public final class Address {

    private final String line1;
    private final String line2; // optional
    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;

    public Address(String line1,
                   String line2,
                   String city,
                   String state,
                   String postalCode,
                   String country) {
        this.line1 = requireNonBlank(line1, "line1");
        this.city = requireNonBlank(city, "city");
        this.state = requireNonBlank(state, "state");
        this.country = requireNonBlank(country, "country");
        this.postalCode = (postalCode == null || postalCode.isBlank()) ? null : postalCode.trim();
        this.line2 = (line2 == null || line2.isBlank()) ? null : line2.trim();
    }

    private String requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value.trim();
    }

    public String line1() { return line1; }
    public String line2() { return line2; }
    public String city() { return city; }
    public String state() { return state; }
    public String postalCode() { return postalCode; }
    public String country() { return country; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(line1, address.line1)
                && Objects.equals(line2, address.line2)
                && Objects.equals(city, address.city)
                && Objects.equals(state, address.state)
                && Objects.equals(postalCode, address.postalCode)
                && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line1, line2, city, state, postalCode, country);
    }

    @Override
    public String toString() {
        return line1 + (line2 != null ? ", " + line2 : "") +
                ", " + city +
                ", " + state +
                (postalCode != null ? " " + postalCode : "") +
                ", " + country;
    }
}
