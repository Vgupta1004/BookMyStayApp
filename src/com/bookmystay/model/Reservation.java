package com.bookmystay.model;

/**
 * Represents a booking request made by a guest.
 * Stores the guest's name and their preferred room type.
 */
public class Reservation {
    private String guestName;
    private RoomType requestedRoom;

    public Reservation(String guestName, RoomType requestedRoom) {
        this.guestName = guestName;
        this.requestedRoom = requestedRoom;
    }

    public String getGuestName() { return guestName; }
    public RoomType getRequestedRoom() { return requestedRoom; }
}
