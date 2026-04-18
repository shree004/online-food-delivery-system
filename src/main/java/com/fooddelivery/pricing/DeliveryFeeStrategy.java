package com.fooddelivery.pricing;

import com.fooddelivery.domain.Location;

public interface DeliveryFeeStrategy {
    double calculateFee(Location restaurantLocation, Location customerLocation, boolean surge);
}
