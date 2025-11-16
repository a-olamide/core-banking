package com.decksoft.accountservice.account.domain.model;

import common.domain.AccountId;
import common.domain.CustomerId;
import common.domain.Money;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private final AccountId accountId;
    private final CustomerId customerId;
    private final AccountNumber accountNumber;
    private AccountType type;
    private AccountStatus status;
    private Money ledgerBalance;
    private Money availableBalance;
    private final List<Hold> holds;
    private long version;

    public Account(AccountId accountId,
                   CustomerId customerId,
                   AccountNumber accountNumber,
                   AccountType type,
                   AccountStatus status,
                   Money ledgerBalance,
                   Money availableBalance,
                   List<Hold> holds,
                   long version) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.status = status;
        this.ledgerBalance = ledgerBalance;
        this.availableBalance = availableBalance;
        this.holds = new ArrayList<>(holds);
        this.version = version;
    }

    public static Account open(CustomerId customerId,
                               AccountNumber accountNumber,
                               AccountType type,
                               Money openingBalance) {
        return new Account(
                AccountId.newId(),
                customerId,
                accountNumber,
                type,
                AccountStatus.ACTIVE,
                openingBalance,
                openingBalance,
                List.of(),
                0L
        );
    }

    public AccountId getAccountId() { return accountId; }
    public CustomerId getCustomerId() { return customerId; }
    public AccountNumber getAccountNumber() { return accountNumber; }
    public AccountType getType() { return type; }
    public AccountStatus getStatus() { return status; }
    public Money getLedgerBalance() { return ledgerBalance; }
    public Money getAvailableBalance() { return availableBalance; }
    public long getVersion() { return version; }

    // later: methods like reserveFunds(), applyDebit(), applyCredit(), etc.
}