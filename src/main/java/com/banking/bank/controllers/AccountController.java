package com.banking.bank.controllers;

import com.banking.bank.dto.CreateAccountDTO;
import com.banking.bank.response.GenericResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @PostMapping("")
    public GenericResponse createAccount (@Valid @RequestBody CreateAccountDTO createAccountDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Hesap oluşturma işlemleri burada yapılır
        return  GenericResponse.ok(username, "successfully");
    }


}
