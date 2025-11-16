package common.domain;

import java.math.BigDecimal;
import java.util.Objects;

public record Money(BigDecimal amount, String currency) {

    public Money {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(currency, "currency");
    }

    public static Money of(String amount, String currency) {
        return new Money(new BigDecimal(amount), currency);
    }

    public Money add(Money other) {
        requireSameCurrency(other);
        return new Money(amount.add(other.amount), currency);
    }

    public Money subtract(Money other) {
        requireSameCurrency(other);
        return new Money(amount.subtract(other.amount), currency);
    }

    public Money negate() {
        return new Money(amount.negate(), currency);
    }

    private void requireSameCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currency mismatch: %s vs %s"
                    .formatted(this.currency, other.currency));
        }
    }
}
