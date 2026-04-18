package com.fooddelivery.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private final String id;
    private final String name;
    private final Location location;
    private final List<MenuItem> menu;
    private boolean open;

    public Restaurant(String id, String name, Location location, boolean open) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.open = open;
        this.menu = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
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

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<MenuItem> getMenu() {
        return Collections.unmodifiableList(menu);
    }
}
