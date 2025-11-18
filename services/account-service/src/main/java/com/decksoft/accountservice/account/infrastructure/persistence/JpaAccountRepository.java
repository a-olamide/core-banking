package com.decksoft.accountservice.infrastructure.persistence;

import com.decksoft.accountservice.account.domain.model.Account;
import com.decksoft.accountservice.account.domain.model.AccountNumber;
import com.decksoft.accountservice.account.domain.model.AccountStatus;
import com.decksoft.accountservice.account.domain.model.AccountType;
import com.decksoft.accountservice.account.domain.port.AccountRepository;
import common.domain.AccountId;
import common.domain.CustomerId;
import common.domain.Money;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaAccountRepository implements AccountRepository {

    private final SpringDataAccountRepository jpa;

    public JpaAccountRepository(SpringDataAccountRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Account save(Account account) {
        AccountEntity entity = toEntity(account);
        AccountEntity saved = jpa.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Account> findById(AccountId accountId) {
        return jpa.findById(accountId.value()).map(this::toDomain);
    }

    private AccountEntity toEntity(Account account) {
        AccountEntity e = new AccountEntity();
        e.setAccountId(account.getAccountId().value());
        e.setCustomerId(account.getCustomerId().value());
        e.setAccountNumber(account.getAccountNumber().value());
        e.setType(account.getType().name());
        e.setStatus(account.getStatus().name());
        e.setLedgerBalance(account.getLedgerBalance().amount());
        e.setAvailableBalance(account.getAvailableBalance().amount());
        e.setCurrency(account.getLedgerBalance().currency());
        e.setVersion(account.getVersion());
        return e;
    }

    private Account toDomain(AccountEntity e) {
        return new Account(
                new AccountId(e.getAccountId()),
                new CustomerId(e.getCustomerId()),
                new AccountNumber(e.getAccountNumber()),
                AccountType.valueOf(e.getType()),
                AccountStatus.valueOf(e.getStatus()),
                new Money(e.getLedgerBalance(), e.getCurrency()),
                new Money(e.getAvailableBalance(), e.getCurrency()),
                List.of(), // holds later
                e.getVersion()
        );
    }
}
