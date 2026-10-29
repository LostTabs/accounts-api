package com.losttab.accounts_api.account;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateAccountRequest(
        @NotBlank @Email String ownerEmail,
        @NotNull @PositiveOrZero @Digits(integer = 17, fraction = 2) BigDecimal initialBalance)
{ }