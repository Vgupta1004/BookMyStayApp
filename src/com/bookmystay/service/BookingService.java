package com.bookmystay.service;
import com.bookmystay.model.*;
import java.util.*;

/**
 * Manages the processing of booking requests and room allocation.
 * Key Data Structures:
 * - Queue<Reservation>: FIFO order for fair processing.
 * - Set<String>: Ensures unique Room IDs to prevent double-booking.
 */
public class BookingService {
	
	private Queue<Reservation> requestQueue = new LinkedList<>();
    private Set<String> occupiedRoomIds = new HashSet<>();
    private InventoryService inventory;
    
    public BookingService(InventoryService inventory) {
        this.inventory = inventory;
    }

	/**
     * Adds a new booking request to the end of the queue.
     */
    public void enqueueRequest(Reservation request) {
        requestQueue.add(request);
        System.out.println("Request added to queue for: " + request.getGuestName());
    }
    
    /**
     * Processes the next request in the queue.
     * Flow: Dequeue -> Assign ID -> Add to Set -> Decrement Count.
     */
    public void processNextBooking() {
        if (requestQueue.isEmpty()) {
            System.out.println("No pending requests to process.");
            return;
        }

        Reservation request = requestQueue.poll(); // Dequeue
        RoomType type = request.getRequestedRoom();

        // Check availability one last time before confirming
        if (inventory.getAvailableCount(type) > 0) {
            // Generate a unique Room ID (e.g., SINGLE-101)
            String roomId = type.name() + "-" + (100 + inventory.getAvailableCount(type));
            
            // Prevent double-booking using HashSet
            if (occupiedRoomIds.add(roomId)) { 
                inventory.updateCount(type, inventory.getAvailableCount(type) - 1); // Decrement
                System.out.println("CONFIRMED: " + request.getGuestName() + " assigned to " + roomId);
            }
        } else {
            System.out.println("REJECTED: No " + type + " rooms left for " + request.getGuestName());
        }
    }
    
    /**
     * Returns the number of pending booking requests currently in the queue.
     * This helps the system determine if there is work to be processed
     * * @return the current size of the request queue.
     */
    public int getQueueSize() {
        return requestQueue.size();
    }
    
    /**
     * Processes the next request and returns the unique Room ID assigned.
     * This ID serves as the key for adding services in UC5.
     * @return The unique Room ID (e.g., "SUITE-101") or null if no rooms are available.
     */
    public String processAndReturnId() {
        if (requestQueue.isEmpty()) return null;

        Reservation request = requestQueue.poll(); // FIFO Dequeue
        RoomType type = request.getRequestedRoom();

        if (inventory.getAvailableCount(type) > 0) {
            // Generate Unique ID using room type and current count
            String roomId = type.name() + "-" + (100 + inventory.getAvailableCount(type));
            
            // Add to HashSet to guarantee zero double-booking
            if (occupiedRoomIds.add(roomId)) {
                // Atomic update: decrement inventory count
                inventory.updateCount(type, inventory.getAvailableCount(type) - 1);
                return roomId;
            }
        }
        return null; // Return null if allocation fails 
    }

}
