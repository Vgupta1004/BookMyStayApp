package com.bookmystay;

import com.bookmystay.model.*;
import com.bookmystay.service.*;
import java.util.Scanner;

/**
 * The main entry point for the BookMyStay application.
 * This class-
 *  - Store confirmed reservations
 *  - Support cancellation & review
 *  - Generate reports
 * 
 * @author vgup3012
 * @version 6.0
 */

public class BookMyStayApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // --- UC1: Inventory Setup ---
        InventoryService inventory = new InventoryService();
        inventory.initializeInventory(RoomType.SINGLE, 2, 1500.0);
        inventory.initializeInventory(RoomType.DOUBLE, 1, 2500.0);

        // --- Initialize Services ---
        BookingService bookingService = new BookingService(inventory);
        SearchService searchService = new SearchService(inventory);
        ServiceManagementModule serviceModule = new ServiceManagementModule();
        ReportingService reportingService = new ReportingService();

        System.out.println("=== Welcome to BookMyStay Master App ===");

        // --- UC2 & UC3: Search & Queue ---
        while (true) {
            System.out.println("\n--- Current Availability ---");
            for (RoomType type : RoomType.values()) {
                System.out.println(searchService.getRoomDetails(type));
            }

            System.out.print("\nEnter Guest Name (or 'process' to confirm): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("process")) break;

            System.out.print("Enter Room Type: ");
            try {
                RoomType type = RoomType.valueOf(scanner.nextLine().toUpperCase());
                bookingService.enqueueRequest(new Reservation(name, type));
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }

        // --- UC4, UC5, & UC6: Allocation, Services, and History ---
        System.out.println("\n--- Processing Confirmed Bookings ---");
        while (bookingService.getQueueSize() > 0) {
            // Logic to track the specific reservation before dequeuing for history
            // In a real app, we'd peek or pass the object
            // For this pedagogical tool, we process and record:
            String roomId = bookingService.processAndReturnId(); 
            
            if (roomId != null) {
                // Here we would ideally record the specific reservation object
                System.out.println("Successfully allocated room: " + roomId);
                // Example of adding a default breakfast for history purposes (UC5)
                serviceModule.addServiceToBooking(roomId, new Service("Welcome Drink", 0.0));
            }
        }

        // --- UC6: Final Report ---
        reportingService.printReport();
        
        System.out.println("\nThank you for using BookMyStay!");
        scanner.close();
    }
}