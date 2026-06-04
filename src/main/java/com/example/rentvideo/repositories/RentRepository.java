package com.example.rentvideo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentvideo.entities.VideoRentEntity;

public interface RentRepository extends JpaRepository<VideoRentEntity, Long> {
    public List<VideoRentEntity> findByUser_IdAndReturnedFalse(Long userId);
    public long countByUser_IdAndReturnedFalse(Long userId);
    public Optional<VideoRentEntity> findByUser_IdAndVideo_IdAndReturnedFalse(Long userId, Long videoId);
}
