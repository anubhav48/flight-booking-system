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

        // Flight not found path
        if (flight == null || request.getSeatsRequested() <= 0) {
            return new BookingResponse(null, request.getFlightNumber(), request.getPassengerName(), 0, BookingStatus.FAILED);
        }

        // Atomic seat reduction check
        boolean success = flight.bookSeats(request.getSeatsRequested());

        // Overbooked/Insufficient seats path
        if (!success) {
            return new BookingResponse(null, flight.getFlightNumber(), request.getPassengerName(), 0, BookingStatus.FAILED);
        }

        // Success path
        return new BookingResponse(
                UUID.randomUUID().toString(),
                flight.getFlightNumber(),
                request.getPassengerName(),
                request.getSeatsRequested(),
                BookingStatus.CONFIRMED
        );
    }
}