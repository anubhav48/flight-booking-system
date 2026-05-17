package com.example.flightbooking.controller;

import com.example.flightbooking.dto.BookingRequest;
import com.example.flightbooking.dto.BookingResponse;
import com.example.flightbooking.model.BookingStatus;
import com.example.flightbooking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.processBooking(request);

        // If the business logic set the status to FAILED, return 400 Bad Request
        if (response.getStatus() == BookingStatus.FAILED) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
            // Alternative: HttpStatus.UNPROCESSABLE_ENTITY (422) is also a great choice here
        }

        // Otherwise, return 201 Created for a successful booking
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}