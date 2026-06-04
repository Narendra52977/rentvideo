package com.example.rentvideo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rentvideo.entities.VideoEntity;
import com.example.rentvideo.repositories.VideoRepository;

import lombok.AllArgsConstructor;
import lombok.Value;

@Service
@AllArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    //get all available videos
    public List<VideoEntity> getAllVideos() {
        return videoRepository.findByAvailable(true);
    }
    //add new video
    public VideoEntity addVideo(VideoEntity video) {
        return videoRepository.save(video);
    }
 //
    public VideoEntity getVideoById(Long id) {
        return videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Video not found"));
    }
    public VideoEntity updateVideo(Long id, VideoEntity updatedVideo) {
        VideoEntity existingVideo = videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Video not found"));
        existingVideo.setTitle(updatedVideo.getTitle());
        existingVideo.setDirector(updatedVideo.getDirector());
        existingVideo.setGenre(updatedVideo.getGenre());
        existingVideo.setAvailable(updatedVideo.isAvailable());
        return videoRepository.save(existingVideo);
    }
    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);     
    }
    

}
