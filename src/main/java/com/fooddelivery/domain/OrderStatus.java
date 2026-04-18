package com.fooddelivery.domain;

public enum OrderStatus {
    CREATED,
    CONFIRMED,
    PREPARING,
    PICKED_UP,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELED,
    REFUNDED
}
