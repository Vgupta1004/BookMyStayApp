package com.bookmystay.service;
import com.bookmystay.model.*;
import java.util.*;

/**
 * Service that allows guests to search for available rooms.
 * Key Concepts:
 * - Read-only access: Does not modify the inventory maps.
 * - Defensive checks: Ensures only rooms with count > 0 are shown
 */

public class SearchService {

	private InventoryService inventory;

    public SearchService(InventoryService inventory) {
        this.inventory = inventory;
    }
    
    /**
     * Searches for all room types that currently have availability.
     * Flow: Search request -> HashMap lookup -> Filter available -> Display.
     * @return A list of RoomTypes that are currently available.
     */
    public List<RoomType> getAvailableRoomTypes() {
        List<RoomType> availableRooms = new ArrayList<>();
        
        for (RoomType type : RoomType.values()) {
            if (inventory.getAvailableCount(type) > 0) {
                availableRooms.add(type);
            }
        }
        return availableRooms;
    }
    
    /**
     * Provides a detailed view of a room type including its price.
     * @param type The room type to check.
     * @return A string formatted with price and availability status
     */
    public String getRoomDetails(RoomType type) {
        int count = inventory.getAvailableCount(type);
        double price = inventory.getPrice(type);
        
        if (count > 0) {
            return type + ": $" + price + " (" + count + " available)";
        } else {
            return type + ": Currently Unavailable";
        }
    }

}
