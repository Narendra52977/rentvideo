package com.example.rentvideo.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.rentvideo.dto.UserRequestDto;
import com.example.rentvideo.dto.LoginRequestDto;
import java.util.Optional;
import com.example.rentvideo.entities.UserEntity;
import com.example.rentvideo.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
   public UserEntity registerUser(UserRequestDto user) {
        UserEntity newUser = new UserEntity();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setRole(user.getRole());
        
        return userRepository.save(newUser);
    }
    public UserEntity loginUser(LoginRequestDto loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        UserEntity found = optionalUser.orElseThrow(() -> new ResponseStatusException(
            HttpStatus.UNAUTHORIZED,
            "Email not found"));
        String storedPassword = found.getPassword();
        if (!passwordEncoder.matches(password, storedPassword)) {
            throw new ResponseStatusException(
            HttpStatus.UNAUTHORIZED,
            "Invalid credentials");
            // throw new RuntimeException("Invalid password");
        }
        return found;
    }
    
}
