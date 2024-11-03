package com.banking.bank.dto;

import com.banking.bank.model.UserEntity;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "password cannot be required")
    @Size(min = 10, max = 50, message = "password must be between 10 and 50 characters")
    private String password;

    @NotBlank(message = "email cannot be required")
    @Size(min = 3, max = 45, message = "email must be between 3 and 45 characters")
    @Email(message = "Email should be valid")
    private String email;


    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.setEmail(this.email);
        user.setPassword(this.password);

        return user;
    }
}
