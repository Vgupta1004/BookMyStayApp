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

## Use Case 2: Room Search & Availability Check
### Description
The goal is to Allow guests to search rooms without altering inventory.
Flow:-
 - Search request
 - HashMap lookup
 - Filter available rooms
 - Display

## Use Case 3: Booking Request(First Come First Serve)
### Description
The goal is to Ensure booking fairness during peak demand.
Flow:-
 - Booking Request
 - Enqueue
 - Await processing

## Use Case 4: Reservation Confirmation & Room Allocation
### Description
The goal is to Ensure booking fairness during peak demand.
Flow:-
 - Deque Request
 - Assign room ID
 - Add to Set
 - Decrement count

## Use Case 5: Add-On Service Selection
### Description
The goal is to Ensure booking fairness during peak demand.
Flow:-
 - Select Service
 - Add to List
 - Map to reservation ID

## Use Case 6: Booking History & Reporting
### Description
The goal is to Maintain complete booking history.
Flow:-
 - Confirm booking
 - Add to the list
 - Persist
 - Retrieve when needed
