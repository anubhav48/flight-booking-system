package com.example.flightbooking.controller;

import com.example.flightbooking.dto.BookingRequest;
import com.example.flightbooking.dto.BookingResponse;
import com.example.flightbooking.model.BookingStatus;
import com.example.flightbooking.service.BookingService;
import com.example.flightbooking.service.FlightNotFoundException;
import com.example.flightbooking.service.FlightOverbookedException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/api/bookings")
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.processBooking(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 Created
    }

    @GetMapping("/")
    public ResponseEntity<String> homeWelcome() {
        return ResponseEntity.ok("Welcome to the Flight Booking API System! Use POST to /api/bookings to reserve a seat.");
    }

    // --- Exception Mappings (Building structured FAILED responses safely) ---

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<BookingResponse> handleNotFound(FlightNotFoundException ex) {
        BookingResponse errorBody = new BookingResponse(
                null, ex.getFlightNumber(), ex.getPassengerName(), 0, BookingStatus.FAILED, ex.getMessage()
        );
        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND); // 404 Status
    }

    @ExceptionHandler(FlightOverbookedException.class)
    public ResponseEntity<BookingResponse> handleOverbooked(FlightOverbookedException ex) {
        BookingResponse errorBody = new BookingResponse(
                null, ex.getFlightNumber(), ex.getPassengerName(), 0, BookingStatus.FAILED, ex.getMessage()
        );
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST); // 400 Status
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); // 400 Status
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder("Validation Failed: ");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errorMessage.append(error.getDefaultMessage()).append(" ");
        });
        return new ResponseEntity<>(errorMessage.toString().trim(), HttpStatus.BAD_REQUEST); // 400 Status
    }
}