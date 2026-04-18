package com.fooddelivery.domain;

public class MenuItem {
    private final String id;
    private final String name;
    private final double price;
    private final boolean vegetarian;

    public MenuItem(String id, String name, double price, boolean vegetarian) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vegetarian = vegetarian;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }
}
