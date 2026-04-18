package com.fooddelivery.domain;

public class Coupon {
    private final String code;
    private final double flatDiscount;
    private final double minOrderValue;

    public Coupon(String code, double flatDiscount, double minOrderValue) {
        this.code = code;
        this.flatDiscount = flatDiscount;
        this.minOrderValue = minOrderValue;
    }

    public String getCode() {
        return code;
    }

    public double getFlatDiscount() {
        return flatDiscount;
    }

    public double getMinOrderValue() {
        return minOrderValue;
    }

    public boolean isApplicable(double cartValue) {
        return cartValue >= minOrderValue;
    }
}
