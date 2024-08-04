package prabhakar.myprojects.food_ordering_system.service;

import org.springframework.stereotype.Service;
import prabhakar.myprojects.food_ordering_system.model.Order;
import prabhakar.myprojects.food_ordering_system.model.request.orderRequests.PlaceOrderRequest;

import java.util.List;

@Service
public interface OrderService {

    boolean placeOrder(PlaceOrderRequest placeOrderRequest);
    List<Order> getOrdersByCustomerId(String customerId);
    List<Order> getOrdersByRestaurantId(String restaurantId);
    List<Order> getPendingOrdersByRestaurantId(String restaurantId);
    List<Order> getInProgressOrdersByRestaurantId(String restaurantId);
    List<Order> getCompletedOrdersByRestaurantId(String restaurantId);
    boolean updateOrderStatus(String orderId,String status);
}
