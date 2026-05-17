package com.example.flightbooking.service;

import com.example.flightbooking.dto.BookingRequest;
import com.example.flightbooking.dto.BookingResponse;
import com.example.flightbooking.model.BookingStatus;
import com.example.flightbooking.model.Flight;
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
        flightInventory.put("UA789", new Flight("UA789", 300));
    }

    public BookingResponse processBooking(BookingRequest request) {
        Flight flight = flightInventory.get(request.getFlightNumber());

        if (flight == null) {
            throw new FlightNotFoundException("Flight " + request.getFlightNumber() + " does not exist.");
        }

        if (request.getSeatsRequested() <= 0) {
            throw new IllegalArgumentException("Seat request must be greater than 0.");
        }

        boolean success = flight.bookSeats(request.getSeatsRequested());

        if (!success) {
            throw new FlightOverbookedException("Not enough seats available on flight " + request.getFlightNumber());
        }

        return new BookingResponse(
                UUID.randomUUID().toString(),
                flight.getFlightNumber(),
                request.getPassengerName(),
                request.getSeatsRequested(),
                BookingStatus.CONFIRMED
        );
    }
}
