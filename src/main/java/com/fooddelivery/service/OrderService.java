package com.fooddelivery.service;

import com.fooddelivery.domain.Coupon;
import com.fooddelivery.domain.Customer;
import com.fooddelivery.domain.DeliveryAgent;
import com.fooddelivery.domain.MenuItem;
import com.fooddelivery.domain.Order;
import com.fooddelivery.domain.OrderStatus;
import com.fooddelivery.domain.Restaurant;
import com.fooddelivery.factory.PaymentFactory;
import com.fooddelivery.payment.Payment;
import com.fooddelivery.pricing.DeliveryFeeStrategy;
import com.fooddelivery.pricing.DiscountStrategy;

import java.util.List;
import java.util.UUID;

public class OrderService {
    private final DeliveryFeeStrategy deliveryFeeStrategy;
    private final DiscountStrategy discountStrategy;
    private final PaymentFactory paymentFactory;

    public OrderService(DeliveryFeeStrategy deliveryFeeStrategy, DiscountStrategy discountStrategy, PaymentFactory paymentFactory) {
        this.deliveryFeeStrategy = deliveryFeeStrategy;
        this.discountStrategy = discountStrategy;
        this.paymentFactory = paymentFactory;
    }

    public Order createOrder(Customer customer, Restaurant restaurant, List<MenuItem> items) {
        Order order = new Order(UUID.randomUUID().toString(), customer, restaurant, items);
        customer.addOrderToHistory(order);
        return order;
    }

    public double checkout(Order order, Coupon coupon, String paymentType, boolean surge) {
        double cartValue = order.getCartValue();
        double discount = discountStrategy.calculateDiscount(cartValue, coupon);
        double fee = deliveryFeeStrategy.calculateFee(order.getRestaurant().getLocation(), order.getCustomer().getLocation(), surge);

        if (coupon != null) {
            order.applyCoupon(coupon);
        }

        double payable = Math.max(0.0, cartValue - discount + fee);
        Payment payment = paymentFactory.createPayment(paymentType, UUID.randomUUID().toString(), payable);
        boolean success = payment.process();
        if (!success) {
            throw new IllegalStateException("Payment failed");
        }
        order.setPayment(payment);
        order.updateStatus(OrderStatus.CONFIRMED);
        return payable;
    }

    public void assignDeliveryAgent(Order order, DeliveryAgent agent) {
        if (!agent.isAvailable()) {
            throw new IllegalStateException("Delivery agent is not available.");
        }
        order.assignAgent(agent);
        agent.setAvailable(false);
        order.updateStatus(OrderStatus.PREPARING);
    }

    public void cancelOrder(Order order) {
        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Delivered orders cannot be canceled.");
        }
        order.updateStatus(OrderStatus.CANCELED);
    }

    public void refundOrder(Order order) {
        if (order.getStatus() != OrderStatus.CANCELED) {
            throw new IllegalStateException("Only canceled orders are eligible for refund.");
        }
        order.updateStatus(OrderStatus.REFUNDED);
    }
}
