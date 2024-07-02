package service;

import model.Order;
import repository.impl.OrderRepositoryImpl;

import java.util.ArrayList;

public interface OrderService {
    OrderRepositoryImpl orderRepo=OrderRepositoryImpl.getInstance();

    boolean placeOrder(Order order);
    ArrayList<Order> getOrdersByCustomerId(String customerId);
    ArrayList<Order> getOrdersByRestaurantId(String restaurantId);
    ArrayList<Order> getPendingOrdersByRestaurantId(String restaurantId);
    ArrayList<Order> getInProgressOrdersByRestaurantId(String restaurantId);
    ArrayList<Order> getCompletedOrdersByRestaurantId(String restaurantId);
    boolean updateOrderStatus(String orderId,String status);
}
