package com.bookmystay.service;
import java.util.*;
import com.bookmystay.model.*;

/**
 * Service responsible for managing the hotel's live inventory
 * * Key Data Structures:
 * - HashMap<RoomType, Integer>: Maps room types to available counts for O(1) access
 * - HashMap<RoomType, Double>: Maps room types to their respective prices.
 */

public class InventoryService {
	
	// Room type -> available count
    private Map<RoomType, Integer> roomCounts = new HashMap<>();
    
    // Room type -> price
    private Map<RoomType, Double> roomPrices = new HashMap<>();

    public void initializeInventory(RoomType type, int count, double price) {
        roomCounts.put(type, count);
        roomPrices.put(type, price);
    }
	
	public int getAvailableCount(RoomType type) {
        return roomCounts.getOrDefault(type, 0);
    }

    public double getPrice(RoomType type) {
        return roomPrices.getOrDefault(type, 0.0);
    }
    
    public void updateCount(RoomType type, int newCount) {
        roomCounts.put(type, newCount);
    }

}
