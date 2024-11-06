package com.banking.bank.service;

import com.banking.bank.dto.request.LoginRequest;
import com.banking.bank.dto.request.RegisterRequest;
import com.banking.bank.model.enums.Role;
import com.banking.bank.utils.exception.APIError;
import com.banking.bank.helpers.jwt.JwtService;
import com.banking.bank.model.UserEntity;
import com.banking.bank.repositories.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    public RegisterRequest register(RegisterRequest registerRequest) {
        UserEntity user = modelMapper.map(registerRequest, UserEntity.class);

        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.USER);

        Optional<UserEntity> foundUserName = userRepository.findByUsername(registerRequest.getUsername());
        Optional<UserEntity> foundEmail = userRepository.findByEmail(registerRequest.getEmail());
        if (foundUserName.isPresent() || foundEmail.isPresent()) {
            throw new APIError(501,"Username or email is already taken");
        }
        else {
            UserEntity createdUser = userRepository.save(user);
            return modelMapper.map(createdUser, RegisterRequest.class);
        }
    }


    public String login(LoginRequest loginRequest) {
        UserEntity user = modelMapper.map(loginRequest, UserEntity.class);
        System.out.println(user.getEmail());

        Optional<UserEntity> foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser.isPresent()) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            String token = jwtService.generateToken(foundUser.get());
            return token;
        }
        else {
            throw new APIError(401,"Username or password is incorrect");
        }

    }

    public UserEntity findByEmail(String username) {
        return userRepository.findByEmail(username).orElseThrow(() -> new APIError(404,"User not found"));
    }



}
