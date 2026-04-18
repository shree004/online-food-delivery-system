package com.fooddelivery.payment;

import java.time.Instant;

public class Payment {
    private final String id;
    private final PaymentMethod paymentMethod;
    private final double amount;
    private final Instant paidAt;
    private boolean success;

    public Payment(String id, PaymentMethod paymentMethod, double amount) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paidAt = Instant.now();
    }

    public boolean process() {
        this.success = paymentMethod.pay(amount);
        return success;
    }

    public String getId() {
        return id;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public Instant getPaidAt() {
        return paidAt;
    }

    public boolean isSuccess() {
        return success;
    }
}
