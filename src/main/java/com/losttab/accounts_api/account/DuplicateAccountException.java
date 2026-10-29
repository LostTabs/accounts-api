package com.losttab.accounts_api.account;

public class DuplicateAccountException extends RuntimeException {
    public DuplicateAccountException(String email) { super("Account already exists for " + email); }
}