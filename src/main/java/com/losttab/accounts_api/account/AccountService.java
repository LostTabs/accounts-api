package com.losttab.accounts_api.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository repo;

    public AccountService(AccountRepository repo) { // constructor injection
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public Optional<AccountDto> find(Long id) {
        return repo.findById(id).map(AccountDto::from);
    }

    @Transactional
    public AccountDto create(CreateAccountRequest req) {
        if (repo.findByOwnerEmail(req.ownerEmail()).isPresent()) {
            throw new DuplicateAccountException(req.ownerEmail());
        }
        Account saved = repo.save(new Account(req.ownerEmail(), req.initialBalance()));
        return AccountDto.from(saved);
    }

    @Transactional
    public AccountDto withdraw(Long id, BigDecimal amount) {
        Account account = repo.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        account.withdraw(amount);
        return AccountDto.from(account); // no save() needed
    }
}