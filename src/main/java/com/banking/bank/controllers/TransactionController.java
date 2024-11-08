package com.banking.bank.controllers;

import com.banking.bank.dto.request.TransactionRequest;
import com.banking.bank.dto.response.GetAccountResponse;
import com.banking.bank.dto.response.TransactionResponse;
import com.banking.bank.model.AccountEntity;
import com.banking.bank.model.UserEntity;
import com.banking.bank.service.AccountService;
import com.banking.bank.service.AuthService;
import com.banking.bank.service.TransactionService;
import com.banking.bank.utils.response.GenericResponse;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transactions")
@Data
public class TransactionController {

    private final AuthService authService;
    private final TransactionService transactionService;
    private final AccountService accountService;



    @PostMapping
    public GenericResponse<TransactionResponse> transfer (@Valid @RequestBody TransactionRequest transactionRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        System.out.println(transactionRequest.getAmount());

        UserEntity user = authService.findByEmail(username);

        GetAccountResponse receiverAccount = accountService.getAccountByAccountNumber(transactionRequest.getReceiverAccountNumber());
        GetAccountResponse senderAccount = accountService.getAccountByAccountNumber(transactionRequest.getSenderAccountNumber());

        Boolean isThereAmount = accountService.isThereAmount(transactionRequest.getSenderAccountNumber(),transactionRequest.getAmount());

        if (user != null && isThereAmount) {
            try {
                transactionService.transfer(receiverAccount, senderAccount, user, transactionRequest);
                return GenericResponse.ok(null, "Transaction is successful");
            } catch (Exception e) {
                return GenericResponse.badRequest(null, "error");
            }
        } else {
            return GenericResponse.badRequest(null, "User not found or insufficient balance");
        }








    }




}
