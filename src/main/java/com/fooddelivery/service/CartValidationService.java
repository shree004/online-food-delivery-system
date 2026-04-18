package com.fooddelivery.service;

import com.fooddelivery.domain.MenuItem;
import com.fooddelivery.domain.Restaurant;

import java.util.List;

public class CartValidationService {
    public void validateSingleRestaurantCart(Restaurant restaurant, List<MenuItem> items) {
        boolean allItemsBelong = restaurant.getMenu().containsAll(items);
        if (!allItemsBelong) {
            throw new IllegalArgumentException("Multi-cart is not allowed. Items from another restaurant detected.");
        }
    }
}
