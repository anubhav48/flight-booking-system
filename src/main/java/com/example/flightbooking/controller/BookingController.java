package com.example.flightbooking.controller;

import com.example.flightbooking.dto.BookingRequest;
import com.example.flightbooking.dto.BookingResponse;
import com.example.flightbooking.model.BookingStatus;
import com.example.flightbooking.service.BookingService;
import jakarta.validation.Valid; // Import Valid
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.processBooking(request);

        if (response.getStatus() == BookingStatus.FAILED) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Optional: Intercept Jakarta validation errors nicely
     * If a validation fails, return a 400 Bad Request with the custom validation messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder("Validation Failed: ");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errorMessage.append(error.getDefaultMessage()).append(" ");
        });
        return new ResponseEntity<>(errorMessage.toString().trim(), HttpStatus.BAD_REQUEST);
    }
}