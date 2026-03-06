# BookMyStayApp
A hotel booking app that lets guests search and reserve rooms/services on a first-come, first-served basis. It manages live inventory (room types, counts, prices, amenities), enforces availability holds, processes bookings, and tracks add-on services. Inventory updates atomically as reservations confirm, preventing double-booking.

## Use Case 1: Room inventory Setup and Management
### Description
The goal is to Maintain a single source of truth for hotel room inventory.
Flow:-
 - Add room type
 - Store in HashMap
 - Update Count/Price
 - Confirm