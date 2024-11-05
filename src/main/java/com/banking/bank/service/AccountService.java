package com.banking.bank.service;

import com.banking.bank.dto.request.CreateAccountRequest;
import com.banking.bank.dto.response.CreateAccountResponse;
import com.banking.bank.model.AccountEntity;
import com.banking.bank.model.UserEntity;
import com.banking.bank.repositories.AccountRepository;
import com.banking.bank.repositories.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Data
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest, UserEntity user) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountType(createAccountRequest.getAccountType());
        accountEntity.setUser(user);

        accountEntity.setAccountNumber(11);

        BigDecimal balance = new BigDecimal("500000");
        accountEntity.setBalance(balance);

        AccountEntity result = accountRepository.save(accountEntity);
        CreateAccountResponse createAccountResponse = new CreateAccountResponse();
        createAccountResponse.setAccountNumber(result.getAccountNumber());
        createAccountResponse.setAccountType(result.getAccountType());
        createAccountResponse.setBalance(balance);

        return createAccountResponse;
    }




}
