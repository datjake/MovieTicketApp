package com.example.movieticketapp.models;

public class Movie {
    private String movieId;
    private String title;
    private String description;
    private String imagePoster;

    public Movie() {
        // Bắt buộc phải có constructor rỗng cho Firebase
    }

    public Movie(String title, String description, String imagePoster) {
        this.title = title;
        this.description = description;
        this.imagePoster = imagePoster;
    }

    // Getters and Setters
    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) { this.movieId = movieId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImagePoster() { return imagePoster; }
    public void setImagePoster(String imagePoster) { this.imagePoster = imagePoster; }
}