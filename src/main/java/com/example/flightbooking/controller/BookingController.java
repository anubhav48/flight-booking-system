package com.example.flightbooking.controller;

import com.example.flightbooking.dto.BookingRequest;
import com.example.flightbooking.dto.BookingResponse;
import com.example.flightbooking.model.BookingStatus;
import com.example.flightbooking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/api/bookings")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.processBooking(request);

        if (response.getStatus() == BookingStatus.FAILED) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<String> homeWelcome() {
        return ResponseEntity.ok("Welcome to the Flight Booking API System! Use POST to /api/bookings to reserve a seat!");
    }
}