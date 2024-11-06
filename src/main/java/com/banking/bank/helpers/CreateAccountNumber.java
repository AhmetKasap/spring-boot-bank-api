package com.banking.bank.helpers;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

public class CreateAccountNumber {

    public static String createNumber() {

        String timeComponent = String.valueOf(System.currentTimeMillis()).substring(3); // Son 8 hane

        Random random = new Random();
        String randomComponent = String.format("%04d", random.nextInt(10000)); // 0000 ile 9999 arası

        return timeComponent + randomComponent;
    }
}
