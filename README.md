# BookMyStayApp
A hotel booking app that lets guests search and reserve rooms/services on a first-come, first-served basis. It manages live inventory (room types, counts, prices, amenities), enforces availability holds, processes bookings, and tracks add-on services. Inventory updates atomically as reservations confirm, preventing double-booking.

## Use Case 3: Booking Request(First Come First Serve)
### Description
The goal is to Ensure booking fairness during peak demand.
Flow:-
 - Booking Request
 - Enqueue
 - Await processing