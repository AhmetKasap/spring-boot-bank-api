package com.banking.bank.dto;

import com.banking.bank.model.UserEntity;
import lombok.Data;

@Data
public class UserDTO {
    private String firstName;
    private String lastName;

    private String email;
    private String password;
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
