package com.banking.bank.dto.request;

import com.banking.bank.dto.response.RegisterResponse;
import com.banking.bank.model.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "firstName cannot be required")
    @Size(min = 3, max = 30, message = "firstName must be between 3 and 30 characters")
    private String firstName;

    @NotBlank(message = "lastName cannot be required")
    @Size(min = 3, max = 30, message = "lastName must be between 3 and 30 characters")
    private String lastName;


    @NotBlank(message = "email cannot be required")
    @Size(min = 3, max = 45, message = "email must be between 3 and 45 characters")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "password cannot be required")
    @Size(min = 10, max = 50, message = "password must be between 10 and 50 characters")
    private String password;

    @NotBlank(message = "username cannot be required")
    @Size(min = 3, max = 30, message = "username must be between 3 and 30 characters")
    private String username;



}
