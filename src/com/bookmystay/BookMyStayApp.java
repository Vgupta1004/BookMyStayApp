package com.bookmystay;
import com.bookmystay.model.*;
import com.bookmystay.service.*;
import java.util.*;

/**
 * The main entry point for the BookMyStay application.
 * This class-
 *  - Accepts booking requests
 *  - Enforces arrival order
 *  - Handles high-traffic scenarios
 * 
 * @author vgup3012
 * @version 3.0
 */

public class BookMyStayApp {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
        InventoryService inventory = new InventoryService();
        BookingService bookingService = new BookingService();
        
        // Admin Setup (UC1)
        inventory.initializeInventory(RoomType.SINGLE, 5, 1500.0);
        inventory.initializeInventory(RoomType.DOUBLE, 3, 2500.0);

        System.out.println("--- Welcome to BookMyStay ---");
        
        while (true) {
            System.out.print("\nEnter Guest Name (or type 'exit' to stop): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("exit")) break;

            System.out.println("Available types: SINGLE, DOUBLE, SUITE");
            System.out.print("Enter Room Type: ");
            try {
                RoomType type = RoomType.valueOf(scanner.nextLine().toUpperCase());
                
                // Create and Enqueue Request (UC3)
                Reservation request = new Reservation(name, type);
                bookingService.enqueueRequest(request);
                
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid room type! Please try again.");
            }
        }

        System.out.println("\nTotal requests in queue: " + bookingService.getQueueSize());
    }

}
