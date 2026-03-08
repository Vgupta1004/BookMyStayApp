package com.bookmystay;
import com.bookmystay.model.*;
import com.bookmystay.service.*;

/**
 * The main entry point for the BookMyStay application.
 * This class-
 *  - Display available room types
 *  - Show pricing and amenities
 *  - Prevent booking unavailable rooms
 * 
 * @author vgup3012
 * @version 2.0
 */

public class BookMyStayApp {

	public static void main(String[] args) {
		// UC1 Setup
        InventoryService inventory = new InventoryService();
        inventory.initializeInventory(RoomType.SINGLE, 10, 1500.0);
        inventory.initializeInventory(RoomType.DOUBLE, 0, 2500.0); // No double rooms available

        // UC2: Room Search
        SearchService searchService = new SearchService(inventory);
        
        System.out.println("--- Guest Search Results ---");
        for (RoomType type : searchService.getAvailableRoomTypes()) {
            System.out.println(searchService.getRoomDetails(type));
        }
    }

}
