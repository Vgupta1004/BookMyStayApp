package com.bookmystay;
import com.bookmystay.model.*;
import com.bookmystay.service.*;

/**
 * The main entry point for the BookMyStay application.
 * This class simulates the Hotel Admin actor who initializes the system 
 * requirements for Use Case 1
 * 
 * @author vgup3012
 * @version 1.0
 */

public class BookMyStayApp {

	public static void main(String[] args) {
        InventoryService inventory = new InventoryService();

        // Use Case 1: Room Inventory Setup
        inventory.initializeInventory(RoomType.SINGLE, 10, 1500.0);
        inventory.initializeInventory(RoomType.DOUBLE, 5, 2500.0);
        inventory.initializeInventory(RoomType.SUITE, 2, 5000.0);

        System.out.println("Inventory Initialized successfully.");
        System.out.println("Available Suites: " + inventory.getAvailableCount(RoomType.SUITE));
    }

}
