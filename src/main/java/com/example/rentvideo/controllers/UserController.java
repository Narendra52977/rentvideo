package com.example.rentvideo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rentvideo.dto.LoginRequestDto;
import com.example.rentvideo.dto.UserRequestDto;
import com.example.rentvideo.entities.UserEntity;
import com.example.rentvideo.services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserEntity> loginUser(@Valid @RequestBody LoginRequestDto loginRequest) {
        // Implementation for login
        UserEntity user = userService.loginUser(loginRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser( @Valid @RequestBody UserRequestDto userRequest) {
        if (userService.emailExists(userRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");     

        }
        userService.registerUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
    
}
