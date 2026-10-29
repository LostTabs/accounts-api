package com.losttab.accounts_api.account;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(Long id) { super("Insufficient funds in account " + id); }
}