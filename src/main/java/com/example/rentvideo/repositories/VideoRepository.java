package com.example.rentvideo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentvideo.entities.VideoEntity;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
    List<VideoEntity> findByAvailable(boolean available);
}
