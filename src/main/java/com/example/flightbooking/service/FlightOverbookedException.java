package com.example.flightbooking.service;

public class FlightOverbookedException extends RuntimeException {
    private final String flightNumber;
    private final String passengerName;

    public FlightOverbookedException(String message, String flightNumber, String passengerName) {
        super(message);
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
    }

    public String getFlightNumber() { return flightNumber; }
    public String getPassengerName() { return passengerName; }
}