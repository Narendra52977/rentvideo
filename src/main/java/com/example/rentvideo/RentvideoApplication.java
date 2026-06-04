package com.example.rentvideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// The service must implement authentication and authorization.
// The service uses Basic Auth for authentication.
// The service must have two roles: CUSTOMER and ADMIN.
// The service must have two types of API endpoints:
// Public endpoints: Anyone can access them (e.g., Registration, Login).
// Private endpoints: Only authenticated users can access (e.g., Viewing available videos).
// Private endpoints must also enforce authorization, i.e., only specific roles can access certain endpoints (e.g., managing videos should only be allowed for the ADMIN role).
// The API must have the following features:
// User Registration and Login
// Users must be able to register by providing their email address, password, and role.
// The password must be hashed using BCrypt.
// Fields: Email, Password, First Name, Last Name, Role.
// The Role should default to CUSTOMER if not specified.
// Registered users must log in using their email address and password (Basic Auth).
// Video Management
// The system must store and manage video details.
// Fields: Title, Director, Genre, Availability Status (whether the video is available for rent or not).
// Assume that all videos are available to rent
// Any user can browse the list of available videos.
// Only the ADMIN is allowed to create, update, and delete videos.

@SpringBootApplication
public class RentvideoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentvideoApplication.class, args);
	}

}
