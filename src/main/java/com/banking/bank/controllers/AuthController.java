package com.banking.bank.controllers;

import com.banking.bank.dto.request.LoginRequest;
import com.banking.bank.dto.request.RegisterRequest;
import com.banking.bank.dto.response.RegisterResponse;
import com.banking.bank.utils.response.GenericResponse;
import com.banking.bank.service.AuthService;
import jakarta.validation.Valid;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Data
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;

    @PostMapping("/register")
    public GenericResponse<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        RegisterRequest registered = authService.register(registerRequest);
        RegisterResponse response = modelMapper.map(registered, RegisterResponse.class);

        return GenericResponse.ok(response,"registration created successfully");

    }

    @PostMapping("/login")
    public GenericResponse<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);

        Map<String, String> result = new HashMap<>();
        result.put("token", token);

        return GenericResponse.ok(result,"login successfully");

    }

}
