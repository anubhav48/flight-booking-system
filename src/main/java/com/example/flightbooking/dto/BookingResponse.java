package com.example.flightbooking.dto;

import com.example.flightbooking.model.BookingStatus;

public class BookingResponse {
    private final String bookingId;
    private final String flightNumber;
    private final String passengerName;
    private final int seatsBooked;
    private final BookingStatus status;
    private final String message;

    public BookingResponse(String bookingId, String flightNumber, String passengerName,
                           int seatsBooked, BookingStatus status, String message) {
        this.bookingId = bookingId;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
        this.seatsBooked = seatsBooked;
        this.status = status;
        this.message = message;
    }

    public String getBookingId() { return bookingId; }
    public String getFlightNumber() { return flightNumber; }
    public String getPassengerName() { return passengerName; }
    public int getSeatsBooked() { return seatsBooked; }
    public BookingStatus getStatus() { return status; }
    public String getMessage() { return message; }
}