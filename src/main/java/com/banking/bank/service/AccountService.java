package com.banking.bank.service;

import com.banking.bank.dto.request.CreateAccountRequest;
import com.banking.bank.dto.response.CreateAccountResponse;
import com.banking.bank.dto.response.GetAccountResponse;
import com.banking.bank.helpers.CreateAccountNumber;
import com.banking.bank.model.AccountEntity;
import com.banking.bank.model.UserEntity;
import com.banking.bank.repositories.AccountRepository;
import com.banking.bank.repositories.UserRepository;
import com.banking.bank.utils.exception.APIError;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest, UserEntity user) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountType(createAccountRequest.getAccountType());
        accountEntity.setUser(user);


        accountEntity.setAccountNumber(CreateAccountNumber.createNumber());

        BigDecimal balance = new BigDecimal("500000");
        accountEntity.setBalance(balance);

        AccountEntity result = accountRepository.save(accountEntity);

        return modelMapper.map(result, CreateAccountResponse.class);
    }

    public List getAccountByUsername (UserEntity user) {
        List<AccountEntity> result = accountRepository.findAllByUserId(user.getId());


        if(result.isEmpty()) {
            throw new APIError(404, "Account not found");
        }

        return result.stream()
                .map(account -> modelMapper.map(account, GetAccountResponse.class))
                .collect(Collectors.toList());

    }

    public AccountEntity deleteAccount(Long userId, Long accountId) {

        AccountEntity accountEntity = accountRepository.findByUserIdAndId(userId, accountId)
                .orElseThrow(() -> new APIError(404,"User or accountId not found"));


        accountRepository.deleteById(accountEntity.getId());

        return accountEntity;

    }




}
