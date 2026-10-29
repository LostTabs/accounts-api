package com.losttab.accounts_api.account;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByOwnerEmail(String ownerEmail);
}