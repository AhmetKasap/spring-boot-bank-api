package com.banking.bank.dto;

import com.banking.bank.model.UserEntity;
import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;


    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.setUsername(this.username);
        user.setPassword(this.password);

        return user;
    }
}
