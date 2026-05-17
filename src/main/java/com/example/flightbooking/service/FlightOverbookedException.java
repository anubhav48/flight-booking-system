package com.example.flightbooking.service;

public class FlightOverbookedException extends RuntimeException {
    public FlightOverbookedException(String message) {
        super(message);
    }
}
