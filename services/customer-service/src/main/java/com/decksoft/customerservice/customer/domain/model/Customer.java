package com.decksoft.customerservice.customer.domain.customer;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Optional;

public class Customer {

    private final CustomerId id;
    private Name name;
    private EmailAddress email;
    private PhoneNumber phone;
    private Address address;
    private CustomerStatus status;
    private final OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private long version;

    // Factory for new customer registration
    public static Customer registerNew(
            Name name,
            EmailAddress email,
            PhoneNumber phone,
            Address address,
            OffsetDateTime now
    ) {
        CustomerId id = CustomerId.newId();
        Customer customer = new Customer(
                id,
                name,
                email,
                phone,
                address,
                CustomerStatus.PENDING_VERIFICATION,
                now,
                now,
                0L
        );
        // domain event could be published via callback / domain event mechanism
        return customer;
    }

    public Customer(
            CustomerId id,
            Name name,
            EmailAddress email,
            PhoneNumber phone,
            Address address,
            CustomerStatus status,
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt,
            long version
    ) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
        this.phone = Objects.requireNonNull(phone);
        this.address = Objects.requireNonNull(address);
        this.status = Objects.requireNonNull(status);
        this.createdAt = Objects.requireNonNull(createdAt);
        this.updatedAt = Objects.requireNonNull(updatedAt);
        this.version = version;
    }

    // Behavior methods

    public void activate(OffsetDateTime now) {
        if (status == CustomerStatus.CLOSED) {
            throw new IllegalStateException("Cannot activate a closed customer");
        }
        this.status = CustomerStatus.ACTIVE;
        touch(now);
    }

    public void suspend(OffsetDateTime now, String reason) {
        if (status == CustomerStatus.CLOSED) {
            throw new IllegalStateException("Cannot suspend a closed customer");
        }
        this.status = CustomerStatus.SUSPENDED;
        // TODO: optionally register domain event with reason
        touch(now);
    }

    public void close(OffsetDateTime now) {
        this.status = CustomerStatus.CLOSED;
        touch(now);
    }

    public void updateContactDetails(
            Optional<EmailAddress> newEmail,
            Optional<PhoneNumber> newPhone,
            Optional<Address> newAddress,
            OffsetDateTime now
    ) {
        newEmail.ifPresent(value -> this.email = value);
        newPhone.ifPresent(value -> this.phone = value);
        newAddress.ifPresent(value -> this.address = value);
        touch(now);
    }

    public void updateName(Name newName, OffsetDateTime now) {
        this.name = Objects.requireNonNull(newName);
        touch(now);
    }

    private void touch(OffsetDateTime now) {
        this.updatedAt = now;
        this.version++;
    }

    // Getters (no setters to keep invariants inside aggregate)

    public CustomerId getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public PhoneNumber getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public CustomerStatus getStatus() {
        return status;
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
