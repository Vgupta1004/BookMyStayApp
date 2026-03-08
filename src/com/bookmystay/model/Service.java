package com.bookmystay.model;

/**
 * Represents an optional add-on service for a guest's stay.
 * Supports the extensible service model requirement of UC5.
 */
public class Service {
	
	private String name;
    private double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }

}
