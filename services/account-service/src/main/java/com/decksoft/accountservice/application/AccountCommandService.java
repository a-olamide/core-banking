package com.decksoft.accountservice.application;

import com.decksoft.accountservice.account.domain.model.Account;
import com.decksoft.accountservice.account.domain.model.AccountNumber;
import com.decksoft.accountservice.account.domain.model.AccountType;
import com.decksoft.accountservice.account.domain.port.AccountRepository;
import common.domain.CustomerId;
import common.domain.Money;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountCommandService {

    private final AccountRepository accountRepository;

    public AccountCommandService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account openAccount(CustomerId customerId,
                               AccountNumber accountNumber,
                               AccountType type,
                               Money openingBalance) {
        Account account = Account.open(customerId, accountNumber, type, openingBalance);
        return accountRepository.save(account);
    }
}
