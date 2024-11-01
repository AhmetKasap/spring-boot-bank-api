package com.banking.bank.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateAccountDTO {

    @Pattern(regexp = "VADESIZ|VADELI", message = "Account type must be either VADESIZ or VADELI")
    private String accountType;
}
