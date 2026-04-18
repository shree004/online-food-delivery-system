package com.fooddelivery.pricing;

import com.fooddelivery.domain.Location;

public class StandardDeliveryFeeStrategy implements DeliveryFeeStrategy {
    @Override
    public double calculateFee(Location restaurantLocation, Location customerLocation, boolean surge) {
        double base = restaurantLocation.getArea().equalsIgnoreCase(customerLocation.getArea()) ? 20.0 : 40.0;
        return surge ? base * 1.5 : base;
    }
}
