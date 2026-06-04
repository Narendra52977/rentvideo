package com.example.rentvideo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rentvideo.entities.VideoEntity;
import com.example.rentvideo.services.VideoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class VideoController {
    private final VideoService videoService;
    @GetMapping("/videos")
    public ResponseEntity<List<VideoEntity>> getVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }

    @PostMapping("/video")
    public ResponseEntity<VideoEntity> addVideo(@Valid @RequestBody VideoEntity video) {
        return ResponseEntity.ok(videoService.addVideo(video));
    }

    @PutMapping("/video/{id}")
    public ResponseEntity<VideoEntity> updateVideo(@PathVariable Long id, @Valid @RequestBody VideoEntity video) {
        return ResponseEntity.ok(videoService.updateVideo(id, video));
    }

    @DeleteMapping("/video/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return ResponseEntity.noContent().build();
    }
}
