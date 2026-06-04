package com.example.rentvideo.dto;

import jakarta.validation.constraints.NotBlank;

import com.example.rentvideo.Enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;
    private String lastName;
    
    private Role role = Role.CUSTOMER;
}
