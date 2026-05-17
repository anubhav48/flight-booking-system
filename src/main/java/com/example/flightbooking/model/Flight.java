package com.example.flightbooking.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Flight {
    private final String flightNumber;
    private final int totalCapacity;
    // Encapsulated to maintain absolute thread safety
    private final AtomicInteger availableSeats;

    public Flight(String flightNumber, int totalCapacity) {
        this.flightNumber = flightNumber;
        this.totalCapacity = totalCapacity;
        this.availableSeats = new AtomicInteger(totalCapacity);
    }

    public String getFlightNumber() { return flightNumber; }
    public int getTotalCapacity() { return totalCapacity; }

    // Returns a primitive int value to avoid exposing the mutable AtomicInteger reference
    public int getAvailableSeats() {
        return availableSeats.get();
    }

    public boolean bookSeats(int seatsRequested) {
        while (true) {
            int currentAvailable = availableSeats.get();
            if (currentAvailable < seatsRequested) {
                return false; // Insufficient seats available
            }
            int updatedAvailable = currentAvailable - seatsRequested;
            // Lock-free atomic update transaction
            if (availableSeats.compareAndSet(currentAvailable, updatedAvailable)) {
                return true;
            }
        }
    }
}