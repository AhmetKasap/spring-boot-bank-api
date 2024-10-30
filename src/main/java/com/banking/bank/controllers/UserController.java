package com.banking.bank.controllers;

import com.banking.bank.dto.LoginDTO;
import com.banking.bank.dto.RegisterDTO;
import com.banking.bank.response.GenericResponse;
import com.banking.bank.service.UserService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("users")
public class UserController {
    

    private final UserService userService;

    @PostMapping("/register")
    public GenericResponse<RegisterDTO> register (@Valid @RequestBody RegisterDTO registerDTO) {
        RegisterDTO result = userService.register(registerDTO);
        return GenericResponse.created(result,"successfull");
    }


    @PostMapping("/login")
    public GenericResponse<LoginDTO> login (@RequestBody LoginDTO loginDTO) {
        return null;
    }
}
