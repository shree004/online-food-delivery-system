package com.fooddelivery.app;

import com.fooddelivery.domain.Coupon;
import com.fooddelivery.domain.Customer;
import com.fooddelivery.domain.DeliveryAgent;
import com.fooddelivery.domain.Location;
import com.fooddelivery.domain.MenuItem;
import com.fooddelivery.domain.Order;
import com.fooddelivery.domain.Restaurant;
import com.fooddelivery.factory.PaymentFactory;
import com.fooddelivery.observer.CustomerNotificationObserver;
import com.fooddelivery.pricing.CouponDiscountStrategy;
import com.fooddelivery.pricing.StandardDeliveryFeeStrategy;
import com.fooddelivery.service.CartValidationService;
import com.fooddelivery.service.OrderService;
import com.fooddelivery.service.RestaurantService;
import com.fooddelivery.tracking.LiveTrackingSimulator;

import java.util.List;

public class FoodDeliveryApplication {
    public static void main(String[] args) {
        // Domain setup
        Location hsr = new Location("HSR", "Bengaluru");
        Customer customer = new Customer("C1", "Rahul", hsr);
        DeliveryAgent agent = new DeliveryAgent("D1", "Aman", hsr, true);

        Restaurant restaurant = new Restaurant("R1", "Spice Hub", hsr, true);
        MenuItem biryani = new MenuItem("M1", "Chicken Biryani", 220.0, false);
        MenuItem paneerWrap = new MenuItem("M2", "Paneer Wrap", 160.0, true);
        restaurant.addMenuItem(biryani);
        restaurant.addMenuItem(paneerWrap);

        // Area availability
        RestaurantService restaurantService = new RestaurantService();
        List<Restaurant> available = restaurantService.findAvailableByArea(List.of(restaurant), customer.getLocation());
        if (available.isEmpty()) {
            throw new IllegalStateException("No restaurant available in your area.");
        }

        // Multi-cart validation
        List<MenuItem> cart = List.of(biryani, paneerWrap);
        CartValidationService cartValidationService = new CartValidationService();
        cartValidationService.validateSingleRestaurantCart(restaurant, cart);

        // Order flow with Strategy + Factory + Observer
        OrderService orderService = new OrderService(
                new StandardDeliveryFeeStrategy(),
                new CouponDiscountStrategy(),
                new PaymentFactory()
        );

        Order order = orderService.createOrder(customer, restaurant, cart);
        order.addObserver(new CustomerNotificationObserver());

        Coupon coupon = new Coupon("NEWUSER50", 50.0, 300.0);
        double payable = orderService.checkout(order, coupon, "UPI", true); // surge fee active
        System.out.println("Total payable after discount and surge fee: " + payable);

        // Assign and track
        orderService.assignDeliveryAgent(order, agent);
        LiveTrackingSimulator tracker = new LiveTrackingSimulator();
        tracker.simulate(order);

        // Cancellation/refund sample workflow (second order)
        Order secondOrder = orderService.createOrder(customer, restaurant, List.of(paneerWrap));
        secondOrder.addObserver(new CustomerNotificationObserver());
        orderService.checkout(secondOrder, null, "COD", false);
        orderService.cancelOrder(secondOrder);
        orderService.refundOrder(secondOrder);

        System.out.println("Simulation completed successfully.");
    }
}
