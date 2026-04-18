package com.fooddelivery.domain;

import com.fooddelivery.observer.OrderObserver;
import com.fooddelivery.payment.Payment;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final String id;
    private final Customer customer;
    private final Restaurant restaurant;
    private final List<MenuItem> items;
    private final Instant createdAt;
    private final List<OrderObserver> observers;
    private OrderStatus status;
    private DeliveryAgent assignedAgent;
    private Payment payment;
    private Coupon coupon;

    public Order(String id, Customer customer, Restaurant restaurant, List<MenuItem> items) {
        this.id = id;
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = new ArrayList<>(items);
        this.createdAt = Instant.now();
        this.status = OrderStatus.CREATED;
        this.observers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public double getCartValue() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void updateStatus(OrderStatus newStatus) {
        OrderStatus oldStatus = this.status;
        this.status = newStatus;
        notifyObservers(oldStatus, newStatus);
    }

    public DeliveryAgent getAssignedAgent() {
        return assignedAgent;
    }

    public void assignAgent(DeliveryAgent assignedAgent) {
        this.assignedAgent = assignedAgent;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void applyCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    private void notifyObservers(OrderStatus oldStatus, OrderStatus newStatus) {
        for (OrderObserver observer : observers) {
            observer.onOrderStatusChanged(this, oldStatus, newStatus);
        }
    }
}
