package com.fooddelivery.pricing;

import com.fooddelivery.domain.Coupon;

public class CouponDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(double cartValue, Coupon coupon) {
        if (coupon == null || !coupon.isApplicable(cartValue)) {
            return 0.0;
        }
        return Math.min(coupon.getFlatDiscount(), cartValue);
    }
}
