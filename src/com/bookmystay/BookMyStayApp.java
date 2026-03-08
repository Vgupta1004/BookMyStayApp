package com.bookmystay;
import com.bookmystay.model.*;
import com.bookmystay.service.*;
import java.util.*;

/**
 * The main entry point for the BookMyStay application.
 * This class-
 *  - Assign a unique room ID
 *  - Prevent reuse of room IDs
 *  - Update availability immediately
 * 
 * @author vgup3012
 * @version 4.0
 */

public class BookMyStayApp {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
        
        // UC1: Initialize the Centralized Inventory
        InventoryService inventory = new InventoryService();
        inventory.initializeInventory(RoomType.SINGLE, 2, 1500.0);
        inventory.initializeInventory(RoomType.DOUBLE, 1, 2500.0);
        inventory.initializeInventory(RoomType.SUITE, 1, 5000.0);

        // UC3 & UC4: Initialize Booking Service with Inventory
        BookingService bookingService = new BookingService(inventory);
        SearchService searchService = new SearchService(inventory);

        System.out.println("--- Welcome to BookMyStay App Master ---");
        
        // Guests search and enter the queue on a first-come, first-served basis
        while (true) {
            System.out.println("\n--- Current Available Rooms ---");
            for (RoomType type : RoomType.values()) {
                System.out.println(searchService.getRoomDetails(type));
            }

            System.out.print("\nEnter Guest Name to start booking (or 'process' to confirm all, 'exit' to quit): ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("exit")) break;
            if (input.equalsIgnoreCase("process")) break;

            System.out.print("Enter Room Type (SINGLE, DOUBLE, SUITE): ");
            try {
                String typeInput = scanner.nextLine().toUpperCase();
                RoomType selectedType = RoomType.valueOf(typeInput);
                
                // Enforce arrival order by adding to Queue
                Reservation request = new Reservation(input, selectedType);
                bookingService.enqueueRequest(request);
                
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid room type! Please choose from the available options.");
            }
        }
        
        // Process the queue to guarantee zero double-booking
        System.out.println("\n--- Processing Reservation Confirmation & Room Allocation ---");
        int initialQueueSize = bookingService.getQueueSize();
        
        for (int i = 0; i < initialQueueSize; i++) {
            // Dequeue -> Assign unique Room ID -> Update availability immediately
            bookingService.processNextBooking();
        }

        System.out.println("\nFinal Inventory Status:");
        for (RoomType type : RoomType.values()) {
            System.out.println(type + " remaining: " + inventory.getAvailableCount(type));
        }
    }

}
