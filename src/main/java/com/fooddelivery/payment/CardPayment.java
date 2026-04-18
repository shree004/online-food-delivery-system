package com.fooddelivery.payment;

public class CardPayment implements PaymentMethod {
    @Override
    public boolean pay(double amount) {
        return amount > 0;
    }

    @Override
    public String methodName() {
        return "CARD";
    }
}
