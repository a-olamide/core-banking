package com.decksoft.accountservice.api;

import com.decksoft.accountservice.account.domain.model.Account;
import com.decksoft.accountservice.account.domain.model.AccountNumber;
import com.decksoft.accountservice.account.domain.model.AccountType;
import com.decksoft.accountservice.api.dto.AccountResponse;
import com.decksoft.accountservice.api.dto.CreateAccountRequest;
import com.decksoft.accountservice.application.AccountCommandService;
import common.domain.CustomerId;
import common.domain.Money;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/internal/accounts")
public class AccountController {

    private final AccountCommandService accountCommandService;

    public AccountController(AccountCommandService accountCommandService) {
        this.accountCommandService = accountCommandService;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> openAccount(@Valid @RequestBody CreateAccountRequest req) {
        var customerId = new CustomerId(UUID.fromString(req.customerId()));
        var accountNumber = new AccountNumber(req.accountNumber());
        var type = AccountType.valueOf(req.type());
        var openingBalance = new Money(req.openingBalance(), req.currency());

        Account account = accountCommandService.openAccount(customerId, accountNumber, type, openingBalance);

        return ResponseEntity.ok(toResponse(account));
    }

    private AccountResponse toResponse(Account a) {
        return new AccountResponse(
                a.getAccountId().value().toString(),
                a.getCustomerId().value().toString(),
                a.getAccountNumber().value(),
                a.getType().name(),
                a.getStatus().name(),
                a.getLedgerBalance().amount(),
                a.getAvailableBalance().amount(),
                a.getLedgerBalance().currency()
        );
    }
}
