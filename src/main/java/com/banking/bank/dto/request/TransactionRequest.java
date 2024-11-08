package com.banking.bank.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    private final String senderAccountNumber;
    private final String receiverAccountNumber;
    private final BigDecimal amount;
    private final String explantaion;
}
