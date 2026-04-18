package com.fooddelivery.service;

import com.fooddelivery.domain.Location;
import com.fooddelivery.domain.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantService {
    public List<Restaurant> findAvailableByArea(List<Restaurant> restaurants, Location customerLocation) {
        return restaurants.stream()
                .filter(Restaurant::isOpen)
                .filter(r -> r.getLocation().getArea().equalsIgnoreCase(customerLocation.getArea()))
                .collect(Collectors.toList());
    }
}
