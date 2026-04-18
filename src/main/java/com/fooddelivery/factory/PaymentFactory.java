package com.fooddelivery.factory;

import com.fooddelivery.payment.CardPayment;
import com.fooddelivery.payment.CashOnDeliveryPayment;
import com.fooddelivery.payment.Payment;
import com.fooddelivery.payment.PaymentMethod;
import com.fooddelivery.payment.UpiPayment;

public class PaymentFactory {
    public Payment createPayment(String paymentType, String paymentId, double amount) {
        PaymentMethod paymentMethod = switch (paymentType.toUpperCase()) {
            case "CARD" -> new CardPayment();
            case "UPI" -> new UpiPayment();
            case "COD" -> new CashOnDeliveryPayment();
            default -> throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        };

        return new Payment(paymentId, paymentMethod, amount);
    }
}
