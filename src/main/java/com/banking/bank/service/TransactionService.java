package com.banking.bank.service;

import com.banking.bank.dto.request.TransactionRequest;
import com.banking.bank.dto.response.GetAccountResponse;
import com.banking.bank.model.AccountEntity;
import com.banking.bank.model.TransactionEntity;
import com.banking.bank.model.UserEntity;
import com.banking.bank.repositories.AccountRepository;
import com.banking.bank.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Service
public class TransactionService {

    private final AccountService accountService;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public Boolean transfer(GetAccountResponse receiverAccount, GetAccountResponse senderAccount, UserEntity user, TransactionRequest transactionRequest) {

        BigDecimal amount = transactionRequest.getAmount();


        AccountEntity receiver = modelMapper.map(receiverAccount, AccountEntity.class);
        AccountEntity sender = modelMapper.map(senderAccount, AccountEntity.class);


        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));

        accountRepository.save(receiver);
        accountRepository.save(sender);

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setUser(user);
        transactionEntity.setAmount(amount);
        transactionEntity.setExplantaion(transactionRequest.getExplantaion());
        transactionEntity.setReceiverAccount(receiver);
        transactionEntity.setSenderAccount(sender);
        transactionEntity.setTransactionDate(LocalDateTime.now());


        transactionRepository.save(transactionEntity);

        return true;

    }


}
