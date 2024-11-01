package com.banking.bank.controllers;

import com.banking.bank.dto.LoginDTO;
import com.banking.bank.dto.RegisterDTO;
import com.banking.bank.response.GenericResponse;
import com.banking.bank.service.AuthService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Data
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public GenericResponse<RegisterDTO> register(@Valid @RequestBody RegisterDTO registerDTO) {
        RegisterDTO registered = authService.register(registerDTO);
        return GenericResponse.ok(registered,"registration created successfully");

    }

    @PostMapping("/login")
    public GenericResponse<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO);

        Map<String, String> result = new HashMap<>();
        result.put("token", token);

        return GenericResponse.ok(result,"login successfully");

    }

}
