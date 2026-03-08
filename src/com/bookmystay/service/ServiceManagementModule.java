package com.bookmystay.service;
import com.bookmystay.model.*;
import java.util.*;

/**
 * Manages guest add-on services using a Map for one-to-many relationships.
 * Key Data Structure: Map<String, List<Service>>.
 */
public class ServiceManagementModule {
	
	private Map<String, List<Service>> guestServices = new HashMap<>();

	/**
     * Attaches a service to a specific reservation ID
     * Flow: Select service -> Add to List -> Map to reservation ID
     */
    public void addServiceToBooking(String reservationId, Service service) {
        // computeIfAbsent creates the list if it doesn't exist for this ID yet
        guestServices.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
        System.out.println("Service '" + service + "' added to booking: " + reservationId);
    }
    
    /**
     * Retrieves all services attached to a specific booking
     */
    public List<Service> getServicesForBooking(String reservationId) {
        return guestServices.getOrDefault(reservationId, new ArrayList<>());
    }

}
