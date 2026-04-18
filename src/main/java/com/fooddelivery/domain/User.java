package com.fooddelivery.domain;

public abstract class User {
    private final String id;
    private final String name;
    private Location location;

    protected User(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void updateLocation(Location location) {
        this.location = location;
    }
}
