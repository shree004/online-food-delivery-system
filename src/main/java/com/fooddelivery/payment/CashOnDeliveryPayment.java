package com.fooddelivery.payment;

public class CashOnDeliveryPayment implements PaymentMethod {
    @Override
    public boolean pay(double amount) {
        return true;
    }

    @Override
    public String methodName() {
        return "COD";
    }
}
