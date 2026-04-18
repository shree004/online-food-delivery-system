package com.fooddelivery.observer;

import com.fooddelivery.domain.Order;
import com.fooddelivery.domain.OrderStatus;

public interface OrderObserver {
    void onOrderStatusChanged(Order order, OrderStatus oldStatus, OrderStatus newStatus);
}
