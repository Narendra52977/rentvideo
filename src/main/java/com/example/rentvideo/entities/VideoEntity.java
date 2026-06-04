package com.example.rentvideo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
// import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "videos")
@NoArgsConstructor
@Data
public class VideoEntity {
    // Fields: Title, Director, Genre, Availability Status (whether the video is
    // available for rent or not).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    private String director;
    private String genre;
    private boolean available = true;
}
