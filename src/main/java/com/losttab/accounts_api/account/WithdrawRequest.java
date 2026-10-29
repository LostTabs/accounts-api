package com.losttab.accounts_api.account;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record WithdrawRequest(@NotNull @Positive @Digits(integer = 17, fraction = 2) BigDecimal amount) { }
