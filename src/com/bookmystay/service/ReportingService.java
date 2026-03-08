package com.bookmystay.service;

import com.bookmystay.model.Reservation;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for tracking booking history and generating reports.
 * Key Data Structure: List<Reservation> for ordered storage.
 */
public class ReportingService {
    private List<Reservation> history = new ArrayList<>();

    /**
     * Adds a confirmed booking to the history for audit purposes.
     */
    public void recordBooking(Reservation reservation) {
        history.add(reservation);
    }

    /**
     * Generates a report of all confirmed bookings.
     */
    public void printReport() {
        System.out.println("\n--- FINAL BOOKING REPORT (UC6) ---");
        if (history.isEmpty()) {
            System.out.println("No confirmed bookings found.");
            return;
        }
        for (Reservation res : history) {
            System.out.println("Guest: " + res.getGuestName() + " | Room: " + res.getRequestedRoom());
        }
    }
}