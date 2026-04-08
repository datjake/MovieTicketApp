package com.example.movieticketapp.models;

public class Ticket {
    private String ticketId;
    private String userId;
    private String movieId;
    private String seatNumber;

    public Ticket() {
    }

    public Ticket(String userId, String movieId, String seatNumber) {
        this.userId = userId;
        this.movieId = movieId;
        this.seatNumber = seatNumber;
    }

    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) { this.movieId = movieId; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
}