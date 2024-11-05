package com.banking.bank.model;

import com.banking.bank.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name="account")
@Data
public class AccountEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    AccountType accountType;

    @Column(unique=true)
    private long accountNumber;

    private BigDecimal balance;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
