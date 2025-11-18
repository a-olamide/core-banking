package com.decksoft.ledgerservice.ledger.domian.ledgeraccount;


import java.util.Optional;

public interface LedgerAccountRepository {

    Optional<LedgerAccount> findByCode(String code);

    Optional<LedgerAccount> findById(LedgerAccountId id);

    LedgerAccount save(LedgerAccount account);
}
