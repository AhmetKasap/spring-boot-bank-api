package com.banking.bank.dto;

import com.banking.bank.model.UserEntity;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "password cannot be required")
    @Size(min = 10, max = 50, message = "password must be between 10 and 50 characters")
    private String password;

    @NotBlank(message = "username cannot be required")
    @Size(min = 3, max = 30, message = "username must be between 3 and 30 characters")
    private String username;


    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.setUsername(this.username);
        user.setPassword(this.password);

        return user;
    }
}
