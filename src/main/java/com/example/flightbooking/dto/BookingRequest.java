package com.example.flightbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class BookingRequest {

    @NotBlank(message = "Flight number cannot be blank.")
    private String flightNumber;

    @NotBlank(message = "Passenger name cannot be blank.")
    private String passengerName;

    @Positive(message = "Seats requested must be a positive number greater than zero.")
    private int seatsRequested;

    // Getters and Setters
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }
    public int getSeatsRequested() { return seatsRequested; }
    public void setSeatsRequested(int seatsRequested) { this.seatsRequested = seatsRequested; }
}