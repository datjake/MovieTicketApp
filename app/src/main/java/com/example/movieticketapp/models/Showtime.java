package com.example.movieticketapp.models;

public class Showtime {
    private String showtimeId;
    private String date;
    private String time;

    public Showtime() {
    }

    public String getShowtimeId() { return showtimeId; }
    public void setShowtimeId(String showtimeId) { this.showtimeId = showtimeId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}