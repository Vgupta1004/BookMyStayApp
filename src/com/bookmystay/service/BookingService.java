package com.bookmystay.service;
import com.bookmystay.model.*;
import java.util.*;

/**
 * Manages the booking queue using the FCFS principle.
 * Key Data Structure: Queue<Reservation> (LinkedList).
 */
public class BookingService {
	
	private Queue<Reservation> requestQueue = new LinkedList<>();

	/**
     * Adds a new booking request to the end of the queue.
     */
    public void enqueueRequest(Reservation request) {
        requestQueue.add(request);
        System.out.println("Request added to queue for: " + request.getGuestName());
    }
    
    /**
     * Checks how many requests are waiting to be processed.
     */
    public int getQueueSize() {
        return requestQueue.size();
    }

}
