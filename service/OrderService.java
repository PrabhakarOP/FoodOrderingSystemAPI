package service;

import model.Order;
import repository.impl.OrderRepositoryImpl;

import java.util.ArrayList;

public interface OrderService {
    OrderRepositoryImpl orderRepo=new OrderRepositoryImpl();

    boolean placeOrder(Order order);
    ArrayList<Order> getOrdersByCustomerId(String customerId);
    ArrayList<Order> getOrdersByRestaurantId(String restaurantId);
    boolean updateOrderStatus(String orderId,String status);
}
