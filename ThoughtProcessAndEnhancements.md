What I expected from Intial Commit which AI covered 

1)Bookings update through Atomic Integer ,it was updating the reference itself which was fixed in later commits 

2)Seperate DTO,Service,Controller Model

3)Various Exception for Diffrent Requests 

##########################################################################################################################################################################

What it didnt cover 

1)An ENUM which would clearly define stated 

2)Validations on Values using @Valid @NotNull @Positive etc

3)The system threw INTERNAL SERER ERROR itself which needed to be refined

4)A Basic Get call for checking if application works ,also could be useful for future when UI comes

##########################################################################################################################################################################


Future Enhancements :

1)Cabin Tiers: Update the domain model to support distinct pricing tiers, baggage allowances, and availability tracking for Business, Premium Economy, and Coach cabins.

2)The Shift: Real flight bookings don't just jump from non-existent to confirmed. They have lifecycles. We will expand the system to manage a structured transaction state machine

3)Event-Driven Notifications:The system will fire an event to trigger a separate notification microservice that emails or texts the passenger their itinerary.

4)Flight to Store Arrival Departure time,Company,Type,Source,Destination etc and write logics on that

