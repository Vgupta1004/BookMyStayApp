package com.bookmystay;

import com.bookmystay.model.*;
import com.bookmystay.service.*;
import java.util.Scanner;
import java.util.List;

/**
 * The main entry point for the BookMyStay application.
 * This class-
 *  - Attach services (breakfast, spa, pickup)
 *  - Allow multiple services per booking
 *  - Calculate the additional cost
 * 
 * @author vgup3012
 * @version 5.0
 */

public class BookMyStayApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // --- UC1: Inventory Setup ---
        InventoryService inventory = new InventoryService();
        inventory.initializeInventory(RoomType.SINGLE, 5, 1500.0);
        inventory.initializeInventory(RoomType.DOUBLE, 3, 2500.0);
        inventory.initializeInventory(RoomType.SUITE, 2, 5000.0);

        // --- Services Setup ---
        BookingService bookingService = new BookingService(inventory);
        SearchService searchService = new SearchService(inventory);
        ServiceManagementModule serviceModule = new ServiceManagementModule();

        System.out.println("=== BookMyStay App: UC5 Add-On Services ===");

        // --- STEP 1: GUEST REQUESTS (UC3) ---
        System.out.print("Enter Guest Name for booking: ");
        String guestName = scanner.nextLine();
        
        System.out.println("\nAvailable Room Types (UC2):");
        for (RoomType type : RoomType.values()) {
            System.out.println("- " + searchService.getRoomDetails(type));
        }

        System.out.print("\nSelect Room Type (SINGLE/DOUBLE/SUITE): ");
        RoomType selectedType = RoomType.valueOf(scanner.nextLine().toUpperCase());
        
        // Enqueue the request
        Reservation request = new Reservation(guestName, selectedType);
        bookingService.enqueueRequest(request);

        // --- STEP 2: ALLOCATION & CONFIRMATION (UC4) ---
        // Process the queue and retrieve the assigned Room ID
        String confirmedRoomId = bookingService.processAndReturnId(); 

        if (confirmedRoomId != null) {
            // --- STEP 3: ADD-ON SERVICE SELECTION (UC5) ---
            System.out.println("\n--- Add-On Service Selection ---");
            System.out.println("Room " + confirmedRoomId + " confirmed for " + guestName);

            while (true) {
                System.out.println("\nAvailable Services:");
                System.out.println("1. Breakfast ($500)");
                System.out.println("2. Spa Treatment ($2000)");
                System.out.println("3. Airport Pickup ($1200)");
                System.out.println("4. Done (Finish Selection)");
                
                System.out.print("Select service number: ");
                String choice = scanner.nextLine();

                if (choice.equals("4")) break;

                Service selectedService = null;
                switch (choice) {
                    case "1": selectedService = new Service("Breakfast", 500.0); break;
                    case "2": selectedService = new Service("Spa", 2000.0); break;
                    case "3": selectedService = new Service("Airport Pickup", 1200.0); break;
                    default: System.out.println("Invalid choice!"); continue;
                }

                // Attach service to the unique reservation ID
                serviceModule.addServiceToBooking(confirmedRoomId, selectedService);
            }

            // --- STEP 4: FINAL SUMMARY ---
            System.out.println("\n=== FINAL BOOKING SUMMARY ===");
            System.out.println("Guest: " + guestName);
            System.out.println("Room: " + confirmedRoomId);
            List<Service> selectedServices = serviceModule.getServicesForBooking(confirmedRoomId);
            
            if (selectedServices.isEmpty()) {
                System.out.println("Services: None");
            } else {
                System.out.println("Services: " + selectedServices);
            }
        }
    }
}