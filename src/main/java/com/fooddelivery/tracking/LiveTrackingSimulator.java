package com.fooddelivery.tracking;

import com.fooddelivery.domain.Order;
import com.fooddelivery.domain.OrderStatus;

public class LiveTrackingSimulator {
    public void simulate(Order order) {
        move(order, OrderStatus.PICKED_UP);
        move(order, OrderStatus.OUT_FOR_DELIVERY);
        move(order, OrderStatus.DELIVERED);
    }

    private void move(Order order, OrderStatus status) {
        order.updateStatus(status);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
