package com.banking.bank.repositories;

import com.banking.bank.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    List<AccountEntity> findAllByUserId(Long userId);
    Optional<AccountEntity> findByUserIdAndId(Long userId, Long accountId);
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
