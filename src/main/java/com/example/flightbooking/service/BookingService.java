package com.example.flightbooking.service;

import com.example.flightbooking.dto.BookingRequest;
import com.example.flightbooking.dto.BookingResponse;
import com.example.flightbooking.model.Flight;
import com.example.flightbooking.model.BookingStatus;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BookingService {

    private final Map<String, Flight> flightInventory = new ConcurrentHashMap<>();

    @PostConstruct
    public void initDummyFlights() {
        flightInventory.put("AA123", new Flight("AA123", 150));
        flightInventory.put("DL456", new Flight("DL456", 2));
    }

    public BookingResponse processBooking(BookingRequest request) {
        Flight flight = flightInventory.get(request.getFlightNumber());

        // Fail-state 1: Flight does not exist
        if (flight == null) {
            throw new FlightNotFoundException(
                    "Error: Flight " + request.getFlightNumber() + " does not exist.",
                    request.getFlightNumber(),
                    request.getPassengerName()
            );
        }

        // Fail-state 2: Guardrail check for seat counts
        if (request.getSeatsRequested() <= 0) {
            throw new IllegalArgumentException("Error: Seats requested must be greater than zero.");
        }

        boolean success = flight.bookSeats(request.getSeatsRequested());

        // Fail-state 3: Overbooked flight
        if (!success) {
            throw new FlightOverbookedException(
                    "Error: Not enough seats available on flight " + request.getFlightNumber(),
                    flight.getFlightNumber(),
                    request.getPassengerName()
            );
        }

        // Success Path
        return new BookingResponse(
                UUID.randomUUID().toString(),
                flight.getFlightNumber(),
                request.getPassengerName(),
                request.getSeatsRequested(),
                BookingStatus.CONFIRMED,
                "Booking confirmed successfully!"
        );
    }
}