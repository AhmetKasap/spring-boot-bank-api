package com.banking.bank.controllers;

import com.banking.bank.dto.request.CreateAccountRequest;
import com.banking.bank.dto.response.CreateAccountResponse;
import com.banking.bank.dto.response.GetAccountResponse;
import com.banking.bank.model.AccountEntity;
import com.banking.bank.model.UserEntity;
import com.banking.bank.utils.response.GenericResponse;
import com.banking.bank.service.AccountService;
import com.banking.bank.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/accounts")
public class AccountController {


    private final AccountService accountService;
    private final AuthService authService;
    private final ModelMapper modelMapper;


    @Operation
    @PostMapping("")
    public GenericResponse<CreateAccountResponse> createAccount (@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = authService.findByEmail(username);

        CreateAccountResponse result = accountService.createAccount(createAccountRequest, user);

        return GenericResponse.ok(result, "successfully");
    }

    @GetMapping("")
    public GenericResponse <List> getAccountsByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = authService.findByEmail(username);

        List result =  accountService.getAccountByUsername(user);

        return GenericResponse.ok(result,"accounts listed");

    }

    @DeleteMapping("/{accountId}")
    public GenericResponse<AccountEntity> deleteAccount(@PathVariable Long accountId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = authService.findByEmail(username);

        AccountEntity result = accountService.deleteAccount(user.getId(), accountId);

        return GenericResponse.ok(result, "account successfully closed");

    }


}
