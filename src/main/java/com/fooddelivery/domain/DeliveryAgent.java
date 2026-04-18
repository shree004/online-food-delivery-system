package com.fooddelivery.domain;

public class DeliveryAgent extends User {
    private boolean available;

    public DeliveryAgent(String id, String name, Location location, boolean available) {
        super(id, name, location);
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
