package com.banking.bank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private AccountEntity senderAccount;

    @ManyToOne
    private AccountEntity receiverAccount;

    private BigDecimal amount;

    private LocalDateTime transactionDate;

    private String explantaion;



}
