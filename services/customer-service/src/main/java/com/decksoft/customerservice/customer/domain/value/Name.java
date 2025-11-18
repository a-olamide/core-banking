package com.decksoft.customerservice.customer.domain.model.value;

import java.util.Objects;

public final class Name {

    private final String firstName;
    private final String middleName; // optional
    private final String lastName;

    public Name(String firstName, String middleName, String lastName) {
        this.firstName = validateNotBlank(firstName, "firstName");
        this.lastName = validateNotBlank(lastName, "lastName");
        this.middleName = (middleName == null || middleName.isBlank()) ? null : middleName.trim();
    }

    private String validateNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value.trim();
    }

    public String firstName() { return firstName; }
    public String middleName() { return middleName; }
    public String lastName() { return lastName; }

    public String fullName() {
        if (middleName == null) {
            return firstName + " " + lastName;
        }
        return firstName + " " + middleName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name name)) return false;
        return Objects.equals(firstName, name.firstName)
                && Objects.equals(middleName, name.middleName)
                && Objects.equals(lastName, name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName);
    }

    @Override
    public String toString() {
        return fullName();
    }
}
