package com.losttab.accounts_api.account;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ownerEmail;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Version
    private Long version;

    protected Account() { } // JPA requires a no-arg constructor

    public Account(String ownerEmail, BigDecimal balance) {
        this.ownerEmail = ownerEmail;
        this.balance = balance.setScale(2, RoundingMode.UNNECESSARY);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientFundsException(id);
        }
        balance = balance.subtract(amount);
    }

    public Long getId() { return id; }
    public String getOwnerEmail() { return ownerEmail; }
    public BigDecimal getBalance() { return balance; }
}