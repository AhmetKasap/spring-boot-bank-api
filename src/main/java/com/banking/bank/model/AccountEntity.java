package com.banking.bank.model;

import com.banking.bank.model.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name="account")
@Data
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    AccountType accountType;

    @Column(unique=true)
    private String accountNumber;

    private BigDecimal balance;

    @ManyToOne()
    private UserEntity user;


}
