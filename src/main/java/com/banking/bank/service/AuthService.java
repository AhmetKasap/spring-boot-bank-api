package com.banking.bank.service;

import com.banking.bank.dto.LoginDTO;
import com.banking.bank.dto.RegisterDTO;
import com.banking.bank.enums.Role;
import com.banking.bank.exception.APIError;
import com.banking.bank.model.UserEntity;
import com.banking.bank.repositories.UserRepository;
import com.banking.bank.response.GenericResponse;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;
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

    public RegisterDTO register(RegisterDTO registerDTO) {
        UserEntity user = registerDTO.toEntity();
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(Role.USER);

        Optional<UserEntity> foundUserName = userRepository.findByUsername(registerDTO.getUsername());
        Optional<UserEntity> foundEmail = userRepository.findByEmail(registerDTO.getEmail());
        if (foundUserName.isPresent() || foundEmail.isPresent()) {
            throw new APIError(501,"Username or email is already taken");
        }
        else {
            UserEntity createdUser = userRepository.save(user);
            return createdUser.toDTO();
        }
    }


    public String login(LoginDTO loginDTO) {
        UserEntity user = loginDTO.toEntity();

        Optional<UserEntity> foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser.isPresent()) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            String token = jwtService.generateToken(foundUser.get());
            return token;
        }
        else {
            throw new APIError(401,"Username or password is incorrect");
        }

    }








    /*
    * public RegisterDTO register(RegisterDTO userDTO) {
        UserEntity user = userDTO.toEntity();

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.setRole(Role.USER);


        Optional<UserEntity> foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser.isPresent()) {
            return foundUser.get().toDTO();
        }
        userRepository.save(user);
        return user.toDTO();
    }



    *
    *
    *
    *
    *
    *
    *
    *
    *
    * */





}
