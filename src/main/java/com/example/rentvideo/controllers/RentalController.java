package com.example.rentvideo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rentvideo.entities.VideoRentEntity;
import com.example.rentvideo.services.RentVideoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RentalController {
    private final RentVideoService rentService;
    // @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/videos/{videoId}/rent")
    public ResponseEntity<VideoRentEntity> rentVideo(@PathVariable Long videoId, Authentication authentication) {
        String email = authentication.getName();
        VideoRentEntity videoRent = rentService.rentVideo(videoId, email);
        return ResponseEntity.ok(videoRent);
    }

    @PostMapping("/videos/{videoId}/return")
    public ResponseEntity<VideoRentEntity> returnVideo(@PathVariable Long videoId, Authentication authentication) {
        String email = authentication.getName();
        VideoRentEntity videoRent = rentService.returnVideo(videoId, email);
        return ResponseEntity.ok(videoRent);    
    }
}
