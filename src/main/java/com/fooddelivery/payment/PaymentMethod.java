package com.fooddelivery.payment;

public interface PaymentMethod {
    boolean pay(double amount);
    String methodName();
}
