package com.banking.bank.controllers;

import com.banking.bank.dto.LoginDTO;
import com.banking.bank.dto.UserDTO;
import com.banking.bank.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String test() {
        return "test";
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        UserDTO registered = userService.register(userDTO);
        return ResponseEntity.ok(registered);

    }

    @PostMapping("/login")
    public ResponseEntity<String > login(@RequestBody LoginDTO loginDTO) {
        String result = userService.login(loginDTO);
        return ResponseEntity.ok(result);

    }


}
