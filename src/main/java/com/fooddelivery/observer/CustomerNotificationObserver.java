package com.fooddelivery.observer;

import com.fooddelivery.domain.Order;
import com.fooddelivery.domain.OrderStatus;

public class CustomerNotificationObserver implements OrderObserver {
    @Override
    public void onOrderStatusChanged(Order order, OrderStatus oldStatus, OrderStatus newStatus) {
        System.out.printf("[Notification] Order %s changed from %s to %s%n", order.getId(), oldStatus, newStatus);
    }
}
