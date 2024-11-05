package com.banking.bank.controllers;

import com.banking.bank.dto.request.CreateAccountRequest;
import com.banking.bank.dto.request.RegisterRequest;
import com.banking.bank.dto.response.CreateAccountResponse;
import com.banking.bank.model.UserEntity;
import com.banking.bank.response.GenericResponse;
import com.banking.bank.service.AccountService;
import com.banking.bank.service.AuthService;
import jakarta.validation.Valid;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/accounts")
public class AccountController {


    private final AccountService accountService;
    private final AuthService authService;
    private final ModelMapper modelMapper;


    @PostMapping("")
    public GenericResponse createAccount (@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = authService.findByEmail(username);

        CreateAccountResponse result = accountService.createAccount(createAccountRequest, user);

        return GenericResponse.ok(result, "successfully");
    }


}
