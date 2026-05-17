package com.example.flightbooking.dto;

public class BookingResponse {
    private final String bookingId;
    private final String flightNumber;
    private final String passengerName;
    private final int seatsBooked;
    private final String status;

    public BookingResponse(String bookingId, String flightNumber, String passengerName, int seatsBooked, String status) {
        this.bookingId = bookingId;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
        this.seatsBooked = seatsBooked;
        this.status = status;
    }

    public String getBookingId() { return bookingId; }
    public String getFlightNumber() { return flightNumber; }
    public String getPassengerName() { return passengerName; }
    public int getSeatsBooked() { return seatsBooked; }
    public String getStatus() { return status; }
}
