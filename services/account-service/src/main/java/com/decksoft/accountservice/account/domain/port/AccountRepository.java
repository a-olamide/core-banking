package com.decksoft.accountservice.account.domain.port;

import com.decksoft.accountservice.account.domain.model.Account;
import common.domain.AccountId;

import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(AccountId accountId);
}
