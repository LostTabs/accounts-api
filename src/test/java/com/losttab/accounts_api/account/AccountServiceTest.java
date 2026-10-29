package com.losttab.accounts_api.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock AccountRepository repo;
    @InjectMocks AccountService service;

    @Test
    void withdrawReducesBalance() {
        when(repo.findById(1L)).thenReturn(Optional.of(new Account("a@b.com", new BigDecimal("100"))));
        AccountDto result = service.withdraw(1L, new BigDecimal("30"));
        assertThat(result.balance()).isEqualByComparingTo("70");
    }

    @Test
    void withdrawRejectsOverdraft() {
        when(repo.findById(1L)).thenReturn(Optional.of(new Account("a@b.com", new BigDecimal("100"))));
        assertThatThrownBy(() -> service.withdraw(1L, new BigDecimal("150")))
                .isInstanceOf(InsufficientFundsException.class);
    }

    @Test
    void createRejectsDuplicateEmail() {
        when(repo.findByOwnerEmail("a@b.com")).thenReturn(Optional.of(new Account("a@b.com", BigDecimal.TEN)));
        assertThatThrownBy(() -> service.create(new CreateAccountRequest("a@b.com", BigDecimal.TEN)))
                .isInstanceOf(DuplicateAccountException.class);
        verify(repo, never()).save(any());
    }
}