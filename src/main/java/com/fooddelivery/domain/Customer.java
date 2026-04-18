package com.fooddelivery.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer extends User {
    private final List<Order> orderHistory = new ArrayList<>();

    public Customer(String id, String name, Location location) {
        super(id, name, location);
    }

    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return Collections.unmodifiableList(orderHistory);
    }
}
