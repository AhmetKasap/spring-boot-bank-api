package com.banking.bank.model;

import com.banking.bank.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "firstName cannot be required")
    @Size(min = 3, max = 30, message = "firstName must be between 3 and 30 characters")
    private String firstName;

    @NotBlank(message = "lastName cannot be required")
    @Size(min = 3, max = 30, message = "lastName must be between 3 and 30 characters")
    private String lastName;

    @NotBlank(message = "email cannot be required")
    @Size(min = 3, max = 45, message = "email must be between 3 and 45 characters")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "username cannot be required")
    @Size(min = 3, max = 30, message = "username must be between 3 and 30 characters")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "password cannot be required")
    @Size(min = 10, max = 100, message = "password must be between 10 and 100 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<AccountEntity> accounts = new ArrayList<>();


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {//"rolleri dönüyoruz
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }





}
