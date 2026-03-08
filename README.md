# BookMyStayApp
A hotel booking app that lets guests search and reserve rooms/services on a first-come, first-served basis. It manages live inventory (room types, counts, prices, amenities), enforces availability holds, processes bookings, and tracks add-on services. Inventory updates atomically as reservations confirm, preventing double-booking.

## Use Case 2: Room Search & Availability Check
### Description
The goal is to Allow guests to search rooms without altering inventory.
Flow:-
 - Search request
 - HashMap lookup
 - Filter available rooms
 - Display