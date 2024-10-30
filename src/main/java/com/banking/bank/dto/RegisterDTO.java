package com.banking.bank.dto;

import com.banking.bank.model.UserEntity;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterDTO {

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


    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setUsername(this.username);
        return user;
    }

}
