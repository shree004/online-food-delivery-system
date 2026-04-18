package com.fooddelivery.pricing;

import com.fooddelivery.domain.Coupon;

public interface DiscountStrategy {
    double calculateDiscount(double cartValue, Coupon coupon);
}
