Flight Booking API
A high-concurrency Spring Boot REST API for managing flight seat reservations.
=================================================================================================================================================================================
Core Functionalities
Thread-Safe Seat Allocation: Uses lock-free atomic transactions (AtomicInteger and CAS loops) to process simultaneous bookings and eliminate overbooking race conditions.

Fail-Fast Input Validation: Uses Jakarta constraints (@Valid, @NotBlank, @Positive) to intercept bad or missing request data before it hits the business logic layer.

Decoupled Domain Protection: Returns seat availability as a read-only primitive int to preserve strict encapsulation and protect the internal inventory state from external mutation.

Dynamic Exception Mapping: Bridges specific runtime exceptions (FlightNotFoundException, FlightOverbookedException) to semantic HTTP status codes and structured error payloads.

==============================================================================================================================================================================

## 🧪 API Endpoints & Testing

The server runs on port **`8080`** and includes pre-seeded inventory for testing:
*   `AA123` (150 available seats)
*   `DL456` (2 available seats)

### 1. Welcome / Health Check
```bash
curl -i -X GET http://localhost:8080/

2. Successful Reservation
cURL Command:

Bash
curl -i -X POST http://localhost:8080/api/bookings \
  -H "Content-Type: application/json" \
  -d '{"flightNumber": "AA123", "passengerName": "Jane Doe", "seatsRequested": 2}'

3. Error: Flight Not Found
cURL Command:

Bash
curl -i -X POST http://localhost:9090/api/bookings \
  -H "Content-Type: application/json" \
  -d '{"flightNumber": "XYZ999", "passengerName": "Bob Smith", "seatsRequested": 1}'

4. Error: Flight Overbooked
cURL Command:

Bash
curl -i -X POST http://localhost:9090/api/bookings \
  -H "Content-Type: application/json" \
  -d '{"flightNumber": "DL456", "passengerName": "Alice Johnson", "seatsRequested": 5}'

5. Error: Request Input Validation Failed
cURL Command:

Bash
curl -i -X POST http://localhost:9090/api/bookings \
  -H "Content-Type: application/json" \
  -d '{"flightNumber": "AA123", "passengerName": "   ", "seatsRequested": 0}'
