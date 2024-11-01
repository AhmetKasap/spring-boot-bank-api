package com.banking.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue
    private long id;

    //işlem tarihi

}
