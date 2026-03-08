# BookMyStayApp
A hotel booking app that lets guests search and reserve rooms/services on a first-come, first-served basis. It manages live inventory (room types, counts, prices, amenities), enforces availability holds, processes bookings, and tracks add-on services. Inventory updates atomically as reservations confirm, preventing double-booking.

## Use Case 4: Reservation Confirmation & Room Allocation
### Description
The goal is to Ensure booking fairness during peak demand.
Flow:-
 - Deque Request
 - Assign room ID
 - Add to Set
 - Decrement count