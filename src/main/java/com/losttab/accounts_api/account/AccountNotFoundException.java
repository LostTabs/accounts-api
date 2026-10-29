package com.losttab.accounts_api.account;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) { super("Account " + id + " not found"); }
}
