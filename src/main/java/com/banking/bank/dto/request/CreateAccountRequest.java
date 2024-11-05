package com.banking.bank.dto.request;

import com.banking.bank.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CreateAccountRequest {

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
}
