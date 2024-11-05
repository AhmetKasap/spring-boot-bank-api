package com.banking.bank.dto.response;

import com.banking.bank.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountResponse {
    @Enumerated(EnumType.STRING)
    AccountType accountType;

    private long accountNumber;

    private BigDecimal balance;
}
