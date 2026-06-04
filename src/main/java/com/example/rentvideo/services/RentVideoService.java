package com.example.rentvideo.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.rentvideo.entities.UserEntity;
import com.example.rentvideo.entities.VideoEntity;
import com.example.rentvideo.entities.VideoRentEntity;
import com.example.rentvideo.repositories.RentRepository;
import com.example.rentvideo.repositories.UserRepository;
import com.example.rentvideo.repositories.VideoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentVideoService {
    

  private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private final RentRepository videoRentRepository;


    
   public VideoRentEntity rentVideo(
            Long videoId,
            String email) {

        UserEntity user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        VideoEntity video = videoRepository
                .findById(videoId)
                .orElseThrow(() ->
                        new RuntimeException("Video not found"));

        long activeRentals =
                videoRentRepository
                        .countByUser_IdAndReturnedFalse(user.getId());

        if (activeRentals >= 2) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Maximum active rentals reached");
        }
        Optional<VideoRentEntity> existingRent = videoRentRepository
                        .findByUser_IdAndVideo_IdAndReturnedFalse(
                                        user.getId(), videoId);

        if (existingRent.isPresent()) {
                return existingRent.get();
        }

        VideoRentEntity rent =
                new VideoRentEntity();

        rent.setUser(user);
        rent.setVideo(video);
        rent.setRentDate(LocalDateTime.now());
        rent.setReturned(false);

        videoRentRepository.save(rent);
        return rent;
    }

        public VideoRentEntity returnVideo(
                Long videoId,
                String email) {
        
                UserEntity user = userRepository
                        .findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));
        
                VideoEntity video = videoRepository
                        .findById(videoId)
                        .orElseThrow(() ->
                                new RuntimeException("Video not found"));
        
                VideoRentEntity rent =
                        videoRentRepository
                                .findByUser_IdAndVideo_IdAndReturnedFalse(
                                        user.getId(),
                                        video.getId())
                                .orElseThrow(() ->
                                        new RuntimeException("Active rental not found"));
        
                rent.setReturned(true);
                videoRentRepository.save(rent);
                return rent;
        }
}
