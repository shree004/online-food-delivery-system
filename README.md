# Online Food Delivery System (100% Java)

This project is a backend-focused Java implementation of an online food delivery system inspired by Swiggy/Zomato.

## Implemented OOP + Design Concepts

- **Inheritance**: `User` -> `Customer`, `DeliveryAgent`
- **Interfaces**: `PaymentMethod`, `OrderObserver`, strategy interfaces
- **Strategy Pattern**:
  - `DeliveryFeeStrategy` for normal/surge fee logic
  - `DiscountStrategy` for coupon discount logic
- **Observer Pattern**: Real-time order status notifications
- **Factory Pattern**: `PaymentFactory` creates payment implementations (`UPI`, `CARD`, `COD`)

## Advanced Features Included

- Live order tracking simulation (`PICKED_UP` -> `OUT_FOR_DELIVERY` -> `DELIVERED`)
- Restaurant availability check by customer area
- Surge fee handling during checkout
- Multi-cart validation (single-restaurant order enforcement)
- Order cancellation + refund workflow

## Run

```bash
mvn compile
mvn exec:java -Dexec.mainClass=com.fooddelivery.app.FoodDeliveryApplication
```
