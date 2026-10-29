package com.losttab.accounts_api.account;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> get(@PathVariable Long id) {
        return service.find(id).map(ResponseEntity::ok).orElseThrow(() -> new AccountNotFoundException(id));
    }


    @PostMapping
    public ResponseEntity<AccountDto> create(@Valid @RequestBody CreateAccountRequest req) {
        AccountDto created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/accounts/" + created.id())).body(created);
    }

    @PostMapping("/{id}/withdrawals")
    public AccountDto withdraw(@PathVariable Long id, @Valid @RequestBody WithdrawRequest req) {
        return service.withdraw(id, req.amount());
    }
}