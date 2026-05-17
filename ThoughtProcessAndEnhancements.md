## What I Expected from the Initial Commit Which AI Covered

1. Bookings were updated through `AtomicInteger`, but initially it was updating the reference itself, which was fixed in later commits.

2. Separate DTO, Service, Controller, and Model layers.

3. Different exceptions for different request failure scenarios.

---

## What It Didn't Cover

1. An `ENUM` which would clearly define states.

2. Validation on request values using `@Valid`, `@NotNull`, `@Positive`, etc.

3. The system initially threw `INTERNAL SERVER ERROR`, which needed refinement through proper exception handling.

4. A basic `GET` endpoint to verify whether the application is running correctly. This could also be useful later when integrating a UI.

---

## Future Enhancements

1. **Cabin Tiers:** Update the domain model to support distinct pricing tiers, baggage allowances, and availability tracking for Business, Premium Economy, and Coach cabins.

2. **Booking Lifecycle Management:** Real flight bookings do not simply jump from non-existent to confirmed. They follow a lifecycle. The system can be expanded to manage a structured transaction state machine.

3. **Event-Driven Notifications:** The system can publish events to trigger a separate notification microservice that emails or texts passengers their itinerary details.

4. **Expanded Flight Metadata:** Add support for flight arrival/departure times, airline company, aircraft type, source, destination, and build business logic around them.
