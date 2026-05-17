package com.example.flightbooking.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Flight {
    private final String flightNumber;
    private final int totalCapacity;
    private final AtomicInteger availableSeats;

    public Flight(String flightNumber, int totalCapacity) {
        this.flightNumber = flightNumber;
        this.totalCapacity = totalCapacity;
        this.availableSeats = new AtomicInteger(totalCapacity);
    }

    public String getFlightNumber() { return flightNumber; }
    public int getTotalCapacity() { return totalCapacity; }
    public int getAvailableSeats() { return availableSeats.get(); }

    public boolean bookSeats(int seatsToBook) {
        while (true) {
            int currentAvailable = availableSeats.get();
            if (currentAvailable < seatsToBook) {
                return false;
            }
            if (availableSeats.compareAndSet(currentAvailable, currentAvailable - seatsToBook)) {
                return true;
            }
        }
    }
}
