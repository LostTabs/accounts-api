package com.losttab.accounts_api.account;

import java.math.BigDecimal;

public record AccountDto(Long id, String ownerEmail, BigDecimal balance) {
    static AccountDto from(Account a) {
        return new AccountDto(a.getId(), a.getOwnerEmail(), a.getBalance());
    }
}
